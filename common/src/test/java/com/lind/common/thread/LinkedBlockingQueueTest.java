package com.lind.common.thread;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

public class LinkedBlockingQueueTest {
    private static AtomicLong l = new AtomicLong(1);

    private static void print(String p) {
        System.out.println(Thread.currentThread().getName() + ": " + p);
    }

    @Test
    public void block() throws InterruptedException {
        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue(1);
        List<Runnable> list = new ArrayList<>();

        Runnable runnable = new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println("add value");
                linkedBlockingQueue.put("1");
                Thread.sleep(1000);
            }
        };
        Runnable runnableRemove = new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println("add value 2");
                linkedBlockingQueue.put("2");
                Thread.sleep(1000);
            }
        };
        Thread thread1 = new Thread(runnable);
        thread1.run();
        Thread thread2 = new Thread(runnable);
        thread2.run();
        Thread thread3 = new Thread(runnableRemove);
        thread3.run();


        System.out.println("end:" + linkedBlockingQueue.poll());
    }

    @Test
    public void putTest() throws InterruptedException {
        LinkedBlockingQueue<Long> queue = new LinkedBlockingQueue<Long>();
        TakeRunnable takeRunnable = new TakeRunnable(queue);
        Thread takeThread = new Thread(takeRunnable, "TakeThread");
        takeThread.start();

        Thread.sleep(5000);

        OfferRunnable offerRunnable = new OfferRunnable(queue);
        Thread putThread = new Thread(offerRunnable, "PutThread");
        putThread.start();
    }

    private static class OfferRunnable implements Runnable {
        LinkedBlockingQueue<Long> queue;

        OfferRunnable(LinkedBlockingQueue<Long> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            print("put item to queue");
            try {
                queue.put(l.getAndIncrement());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class TakeRunnable implements Runnable {
        LinkedBlockingQueue<Long> queue;

        TakeRunnable(LinkedBlockingQueue<Long> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                print("Waiting item from queue");
                Object o = queue.take();
                print("Item is " + o);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
