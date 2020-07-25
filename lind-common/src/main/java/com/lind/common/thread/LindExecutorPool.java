package com.lind.common.thread;

import com.github.phantomthief.concurrent.AdaptiveExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import static java.util.concurrent.TimeUnit.MINUTES;

/**
 * 线程池封装.
 */
@Slf4j
@Component
public class LindExecutorPool {
    /**
     * 阻塞队列长度.
     */
    private int queueSize = 10;
    /**
     * 核心线程数.
     */
    private int coreSize = 10;
    /**
     * 最大线程数.
     */
    private int maxSize = 20;

    /**
     * 默认线程池任务执行.
     */
    public void defaultExecutor(Runnable runnable) {
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(queueSize);
        ThreadFactory threadFactory = new NameTreadFactory();
        MyIgnorePolicy myIgnorePolicy = new MyIgnorePolicy();
        ExecutorService executor = new ThreadPoolExecutor(coreSize,
                maxSize,
                1,
                MINUTES,
                queue,
                threadFactory,
                myIgnorePolicy);
        executor.execute(runnable);
    }

    /**
     * adaptive多线程执行.
     * 自适应的Executor框架封装
     * 全局最大线程数设置，防止线程泄露
     * 单次任务线程数分配策略定制
     * 更友好的批量提交任务API
     * 只支持Java8
     */
    public void adaptiveExecutor(Collection<Integer> collections, Consumer<Integer> func) {
        AdaptiveExecutor executor = AdaptiveExecutor.newBuilder()
                .withGlobalMaxThread(10) // 全局最大线程数10
                .adaptiveThread(5, 8) // 每5个任务使用一个线程，每次提交任务最多使用8个线程
                .maxThreadAsPossible(6) // 每次提交任务最多使用6个线程，尽可能多的使用多线程（这个和上面策略二选一）
                .build();
        executor.invokeAll(collections, collection -> collections.toString());//输入一个值 ，处理后返回它
        executor.run(collections, func);
    }

    /**
     * 拒绝策略.
     * 大于核心线核数放入阻塞队列，队列满了，建立新的线程，达到最大线程数时，走拒绝策略.
     */
    static class MyIgnorePolicy implements RejectedExecutionHandler {
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            doLog(r, e);
        }

        private void doLog(Runnable r, ThreadPoolExecutor e) {
            // 可做日志记录等
            log.error(r.toString() + " rejected");
        }
    }

    /**
     * 线程池.
     * 阻塞队列满了，从这里取新的线程.
     */
    static class NameTreadFactory implements ThreadFactory {

        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());
            log.debug(t.getName() + " has been created");
            return t;
        }
    }

}
