package com.lind.common.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    /**
     * 容量范围,默认值为 Integer.MAX_VALUE
     */
    private final int capacity = Integer.MAX_VALUE;

    /**
     * 当前队列中元素数量
     */
    private final AtomicInteger count = new AtomicInteger(0);
    /**
     * take, poll等方法的锁
     */
    private final ReentrantLock takeLock = new ReentrantLock();

    /**
     * 获取队列的 Condition（条件）实例
     */
    private final Condition notEmpty = takeLock.newCondition();

    /**
     * put, offer等方法的锁
     */
    private final ReentrantLock putLock = new ReentrantLock();

    /**
     * 插入队列的 Condition（条件）实例
     */
    private final Condition notFull = putLock.newCondition();

    /**
     * Signals a waiting take. Called only from put/offer (which do not
     * otherwise ordinarily lock takeLock.)
     */
    private void signalNotEmpty() {
        final ReentrantLock takeLock = this.takeLock;
        takeLock.lock();
        try {
            notEmpty.signal();
        } finally {
            takeLock.unlock();
        }
    }

    /**
     * 将指定元素插入到此队列的尾部，如有必要，则等待空间变得可用
     */
    public void put(String e) throws InterruptedException {
        //判断添加元素是否为null
        if (e == null)
            throw new NullPointerException();
        int c = -1;

        final ReentrantLock putLock = this.putLock;
        final AtomicInteger count = this.count;
        //获取插入的可中断锁
        putLock.lockInterruptibly();
        try {
            try {
                //判断队列是否已满
                while (count.get() == capacity)
                    //如果已满则阻塞添加线程
                    notFull.await();
            } catch (InterruptedException ie) {
                //失败就唤醒添加线程
                notFull.signal();
                throw ie;
            }
            //添加元素
            insert(e);
            //修改c值
            c = count.getAndIncrement();
            //根据c值判断队列是否已满
            if (c + 1 < capacity)
                //未满则唤醒添加线程
                notFull.signal();
        } finally {
            //释放锁
            putLock.unlock();
        }
        //c等于0代表添加成功
        if (c == 0)
            signalNotEmpty();
    }

    void insert(String e) {

    }
}
