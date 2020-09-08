package com.lind.common;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class ThreadStatusTest {
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
