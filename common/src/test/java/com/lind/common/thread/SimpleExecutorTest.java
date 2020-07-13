package com.lind.common.thread;

import com.github.phantomthief.concurrent.AdaptiveExecutor;
import com.github.phantomthief.pool.Pool;
import com.github.phantomthief.pool.impl.ConcurrencyAwarePool;
import org.junit.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.concurrent.TimeUnit.MINUTES;

public class SimpleExecutorTest {
    /**
     * 1 	corePoolSize 	int 	核心线程池大小
     * 2 	maximumPoolSize 	int 	最大线程池大小
     * 3 	keepAliveTime 	long 	线程最大空闲时间
     * 4 	unit 	TimeUnit 	时间单位
     * 5 	workQueue 	BlockingQueue<Runnable> 	线程等待队列
     * 6 	threadFactory 	ThreadFactory 	线程创建工厂
     * 7 	handler 	RejectedExecutionHandler 	拒绝策略
     */
    @Test
    public void threadPoolExecutor() throws InterruptedException {
        //大于核心线核数放入阻塞队列，队列满了，建立新的线程，达到最大线程数时，走拒绝策略
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(2);
        ThreadFactory threadFactory = new NameTreadFactory();
        MyIgnorePolicy myIgnorePolicy = new MyIgnorePolicy();
        ExecutorService executor = new ThreadPoolExecutor(1,
                1,
                1,
                MINUTES,
                queue,
                threadFactory,
                myIgnorePolicy);
        for (int i = 1; i <= 10; i++) {
            MyTask task = new MyTask(String.valueOf(i));
            executor.execute(task);
        }
        Thread.sleep(10000);

    }

    /**
     * 自适应的Executor框架封装
     * 全局最大线程数设置，防止线程泄露
     * 单次任务线程数分配策略定制
     * 更友好的批量提交任务API
     * 只支持Java8
     */
    @Test
    public void adaptiveExecutor() {
        // 声明executor
        AdaptiveExecutor executor = AdaptiveExecutor.newBuilder()
                .withGlobalMaxThread(10) // 全局最大线程数10
                .adaptiveThread(5, 8) // 每5个任务使用一个线程，每次提交任务最多使用8个线程
                .maxThreadAsPossible(6) // 每次提交任务最多使用6个线程，尽可能多的使用多线程（这个和上面策略二选一）
                .build();

        // 调用
        Collection<Integer> tasks = Arrays.asList(1, 2, 3); // 原始任务

        Map<Integer, String> result = executor.invokeAll(tasks, task -> task + " done."); // 并发执行

        executor.run(tasks, System.out::println); // 并发执行，无返回值
    }

    /**
     * 有序线程池：com.github.phantomthief:simple-pool
     */
    @Test
    public void simplePool() {
        Pool<MyObject> pool = ConcurrencyAwarePool.<MyObject>builder()
                .maxSize(30)
                .minIdle(1)
                .evaluatePeriod(Duration.ofDays(1))
                .simpleThresholdStrategy(10, 0.8)
                .build(MyObject::new);

        pool.supply(o -> o.print());
    }

    /**
     * 拒绝策略.
     */
    public static class MyIgnorePolicy implements RejectedExecutionHandler {

        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            doLog(r, e);
        }

        private void doLog(Runnable r, ThreadPoolExecutor e) {
            // 可做日志记录等
            System.err.println(r.toString() + " rejected");
        }
    }


    /**
     * 线程池.
     */
    static class NameTreadFactory implements ThreadFactory {

        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());
            System.out.println(t.getName() + " has been created");
            return t;
        }
    }

    /**
     * 异步任务.
     */
    static class MyTask implements Runnable {
        private String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(this.toString() + " is running!");
                Thread.sleep(1000); //让任务执行慢点
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
}