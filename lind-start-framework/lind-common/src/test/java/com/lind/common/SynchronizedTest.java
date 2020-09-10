package com.lind.common;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class SynchronizedTest {
    private static int count = 0;
    /**
     * 线程安全的integer
     */
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    @Test
    public void syncTest() throws InterruptedException {
        Runnable t1 = new MyThread();
        for (int i = 0; i < 5; i++) {
            new Thread(t1, "thread" + i).start();
        }
        Thread.sleep(1000);
        // 保证结果是5000000
        System.out.println("result: " + count);
    }

    @Test
    public void noSyncTest() throws InterruptedException {
        Runnable t1 = new NoSync();
        for (int i = 0; i < 5; i++) {
            new Thread(t1, "thread" + i).start();
        }
        Thread.sleep(1000);
        // 产生并发后，结果<5000000
        System.out.println("result: " + count);
        // atomicInteger结果为5000000
        System.out.println("atomicInteger result: " + atomicInteger.getAndIncrement());
    }


    /**
     * 未加锁
     */
    class NoSync implements Runnable {

        @SneakyThrows
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            for (int i = 0; i < 1000000; i++) {
                count++;
                atomicInteger.getAndIncrement();
            }

        }
    }

    /**
     * 加锁
     */
    class MyThread implements Runnable {
        @Override
        public void run() {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName());
                for (int i = 0; i < 1000000; i++)
                    count++;
            }

        }
    }

}
