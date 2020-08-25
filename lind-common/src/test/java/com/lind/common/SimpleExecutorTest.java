package com.lind.common;

import com.github.phantomthief.pool.Pool;
import com.github.phantomthief.pool.impl.ConcurrencyAwarePool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class SimpleExecutorTest {


    @Autowired
    LindExecutorPool lindExecutorPool;

    @Test
    public void threadPoolExecutor() throws InterruptedException {
        //大于核心线核数放入阻塞队列，队列满了，建立新的线程，达到最大线程数时，走拒绝策略
        for (int i = 1; i <= 10; i++) {
            MyTask task = new MyTask(String.valueOf(i));
            lindExecutorPool.defaultExecutor(task);
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
        Collection<Integer> tasks = Arrays.asList(1, 2, 3); // 原始任务
        lindExecutorPool.adaptiveExecutor(tasks, System.out::println); // 并发执行，无返回值
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
