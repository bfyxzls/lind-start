package com.lind.common;

import com.lind.common.minibase.Bytes;
import com.lind.common.minibase.Config;
import com.lind.common.minibase.KeyValue;
import com.lind.common.minibase.MStore;
import com.lind.common.minibase.MiniBase;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MinibaseTest {
    Logger log = LoggerFactory.getLogger(MinibaseTest.class);
    private volatile ConcurrentSkipListMap<Integer, String> kvMap;
    private volatile ConcurrentHashMap<Integer, String> kvHashMap;

    @SneakyThrows
    @Test
    public void write() {
        Config conf = new Config().setDataDir("d:\\miniBase").setMaxMemstoreSize(1).setFlushMaxRetries(1)
                .setMaxDiskFiles(10);
        final MiniBase db = MStore.create(conf).open();

        // Put
        db.put(Bytes.toBytes(1), Bytes.toBytes(2));

        // Scan
        MiniBase.Iter<KeyValue> kv = db.scan();
        while (kv.hasNext()) {
            KeyValue kvalue = kv.next();
            System.out.println(kvalue);
        }

    }

    @SneakyThrows
    @Test
    public void multiple() {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        for (int i = 1; i < 40; i++) {
            pool.submit(new PrintTask(i));
        }
        pool.shutdown();//已经添加到线程池的将继续执行，执行完成后shutdown.
        //assert kvMap.size() == 40;
        while (Thread.activeCount() > 1) { //判断活动线程数量
            Thread.yield(); //线程把CPU时间让掉，让其他或者自己的线程执行，就是不让主线程结束，等待没有活动线程了，再结束
        }

    }

    @Test
    public void skipListTest() {

        for (int i = 1; i < 10000; i++)
            kvHashMap.put(i, "a");
        for (int i = 1; i < 10000; i++)
            kvMap.put(i, "a");
        StopWatch stopwatch = new StopWatch();
        stopwatch.start(" hashmap find");
        kvMap.containsKey(4);
        stopwatch.stop();
        stopwatch.start(" skipList find");
        kvHashMap.contains(4);
        stopwatch.stop();

        log.info("stopwatch result {}", stopwatch.prettyPrint());

    }

    @Before
    public void init() {
        kvMap = new ConcurrentSkipListMap<>();
        kvHashMap = new ConcurrentHashMap<>();
    }

    private class PrintTask implements Runnable {
        private Integer i;

        public PrintTask(Integer i) {
            this.i = i;
        }

        @SneakyThrows
        @Override
        public void run() {
            kvMap.put(i, i.toString());
            System.out.println(i);
            Thread.sleep(1000);
        }
    }
}
