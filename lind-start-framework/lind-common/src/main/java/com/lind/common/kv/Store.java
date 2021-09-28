package com.lind.common.kv;

import cn.hutool.core.date.DateTime;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ConcurrentSkipListMap达到一定大小就持久化到磁盘上.
 */
public class Store {
  static final Long limitSize = 64L;
  // 块存储大小
  private final AtomicLong dataSize = new AtomicLong();
  // 正在持久化
  private final AtomicBoolean isSnapshotFlushing = new AtomicBoolean(false);

  private final ReentrantReadWriteLock updateLock = new ReentrantReadWriteLock();
  Logger logger = LoggerFactory.getLogger(Store.class);
  // 多线程场景下性能比较好的跳跃表
  private volatile ConcurrentSkipListMap<Entity, Entity> kvMap;
  private ExecutorService pool;

  public Store(ExecutorService pool) {
    this.pool = pool;
    dataSize.set(0);
    this.kvMap = new ConcurrentSkipListMap<>();
  }

  @SneakyThrows
  public void addTry(Entity kv) {
    try {
      add(kv);
    } catch (IOException ex) {
      try {
        Thread.sleep(10);
        logger.info("try 1...");
        add(kv);
      } catch (IOException exx) {
        Thread.sleep(100);
        logger.info("try 2...");
        add(kv);
      }
    }
  }

  public void add(Entity kv) throws IOException {

    flushIfNeeded(true);
    updateLock.readLock().lock();
    try {
      Entity prevKeyValue = kvMap.put(kv, kv);
      if (prevKeyValue == null) {
        dataSize.addAndGet(kv.getSerializeSize());
      } else {
        dataSize.addAndGet(kv.getSerializeSize() - prevKeyValue.getSerializeSize());
      }
    } finally {
      updateLock.readLock().unlock();
    }
    flushIfNeeded(false);
  }

  private void flushIfNeeded(boolean shouldBlocking) throws IOException {
    if (dataSize.get() > limitSize) {
      if (isSnapshotFlushing.get() && shouldBlocking) {
        throw new IOException(
            "Memstore is full, currentDataSize=" + dataSize.get() + "B, maxMemstoreSize="
                + limitSize + "B, please wait until the flushing is finished.");
      } else if (isSnapshotFlushing.compareAndSet(false, true)) {
        pool.submit(new FlusherTask());
      }
    }
  }

  private class FlusherTask implements Runnable {
    @SneakyThrows
    @Override
    public void run() {
      // Step.1 memstore snpashot
      updateLock.writeLock().lock();
      try {
        System.out.print("持久化");
        File file = new File("d:\\zzlBase\\" + DateTime.now().getTime() + ".tmp");
        if (!file.getParentFile().exists()) {//父路径不存在
          file.getParentFile().mkdirs();//创建父路径
        }
        OutputStream output = new FileOutputStream(file, true);
        kvMap.forEach((i, o) -> {
          try {
            output.write(o.toBytes());//输出数据
          } catch (IOException ex) {

          }
        });
        output.close();//关闭流

        kvMap = new ConcurrentSkipListMap<>();
        dataSize.set(0); //持久化之后清空它

      } catch (IOException exception) {

      } finally {
        updateLock.writeLock().unlock();
        isSnapshotFlushing.compareAndSet(true, false);
      }
    }
  }
}
