package com.lind.common.kv;

import java.io.IOException;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Store {
  static final Long limitSize = 64L;
  private final AtomicLong dataSize = new AtomicLong();
  private final ReentrantReadWriteLock updateLock = new ReentrantReadWriteLock();

  private volatile ConcurrentSkipListMap<Entity, Entity> kvMap;
  private ExecutorService pool;

  public Store(ExecutorService pool) {

    this.pool = pool;
    dataSize.set(0);
    this.kvMap = new ConcurrentSkipListMap<>();
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
      if (shouldBlocking) {
        throw new IOException(
            "Memstore is full, currentDataSize=" + dataSize.get() + "B, maxMemstoreSize="
                + limitSize + "B, please wait until the flushing is finished.");
      } else {
        pool.submit(new FlusherTask());
      }
    }
  }

  private class FlusherTask implements Runnable {
    @Override
    public void run() {
      // Step.1 memstore snpashot
      updateLock.writeLock().lock();
      try {

        // TODO MemStoreIter may find the kvMap changed ? should synchronize ?
        System.out.print("持久化");
        kvMap = new ConcurrentSkipListMap<>();
        dataSize.set(0); //持久化之后清空它
      } finally {
        updateLock.writeLock().unlock();
      }
    }
  }
}
