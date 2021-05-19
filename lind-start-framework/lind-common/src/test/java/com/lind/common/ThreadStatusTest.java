package com.lind.common;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadStatusTest {
    /**
     * 测试结果，有7个线程执行成功，3个线程走拒绝策略，7个成功的就是池子只能容纳maximumPoolSize+ArrayBlockingQueue的线程.
     * 调整corePoolSize的值，可以加快任务处理，但对rejected没有影响，是否走拒绝策略的依据是maximumPoolSize+ArrayBlockingQueue与你的任务数量的关系
     * 任务数量大于了maximumPoolSize+ArrayBlockingQueue，就被走拒绝策略.
     */
    @SneakyThrows
    @Test
    public void threadPoolExecutor() {
        RejectedExecutionHandler handler = new MyIgnorePolicy();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(3),
                handler);
        for (int i = 1; i <= 10; i++) {
            MyTask task = new MyTask(String.valueOf(i));
            executor.execute(task);
        }

        System.in.read(); //阻塞主线程
    }

    @Test
    public void runnableState() {
        new Thread(() -> {
            System.out.println("current thread state when doing sth : "
                    + Thread.currentThread().getState());//RUNNABLE
        }).start();
    }

    @Test
    public void BlockedState() throws InterruptedException {
        Object lock = new Object();
        //启动一个线程获取锁，然后假装很忙，再也不放手
        new Thread(() -> {
            synchronized (lock) {
                while (true) {
                }
            }
        }).start();

        Thread threadB = new Thread(() -> {
            synchronized (lock) {
                System.out.println("lock acquired!");
            }
        });
        threadB.start();//线程开始后，状态变成RUNNABLE
        TimeUnit.SECONDS.sleep(5L);//让主线程在这暂停5S，此时B线程已经开始执行，尝试去获取锁，当然是获取不到的
        System.out.println(threadB.getState());//BLOCKED
    }

    @Test
    public void timedWaitingState() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                new ValuableResource().doSthTimedWaiting();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(2L);

        System.out.println(thread.getState());//TIMED_WAITING
        TimeUnit.SECONDS.sleep(2L);
        System.out.println(thread.getState());//RUNNABLE
    }

    public static class MyIgnorePolicy implements RejectedExecutionHandler {

        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            doLog(r, e);
        }

        private void doLog(Runnable r, ThreadPoolExecutor e) {
            // 可做日志记录等
            System.err.println(r.toString() + " rejected");
        }
    }

    static class MyTask implements Runnable {
        private String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(this.toString() + " is running!");
                Thread.sleep(3000); //让任务执行慢点
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "MyTask [name=" + name + "]";
        }
    }

    private class ValuableResource {
        private Object lock = new Object();

        /***
         * 线程获取到锁后，锁调用自己的wait方法向当前捏着自己的线程说，放开我，你去等着
         * 线程就会变成WAITING，注意这里的线程同时也会放弃锁的使用权
         * @throws InterruptedException
         */
        public void doSthWaiting() throws InterruptedException {
            synchronized (lock) {
                lock.wait();//放弃当前锁
            }

        }

        public void doSthTimedWaiting() throws InterruptedException {
            synchronized (lock) {
                lock.wait(3000L);
                while (true) {

                }
            }

        }
    }
}
