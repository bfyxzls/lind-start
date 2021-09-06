package com.lind.common.forkjointask;

import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnableTest {
    public static <T> List<List<T>> splitList(List<T> list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return null;
        }
        List<List<T>> result = new ArrayList<List<T>>();
        int size = list.size();
        int count = (size + len - 1) / len;
        for (int i = 0; i < count; i++) {
            List<T> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }


    @SneakyThrows
    @Test
    public void test() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Collection<DoUrl> bufferInserts = new ArrayList<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add((String.valueOf(i)));
        }

        // 将大集合分成5份，每份20个数据，这样有5个线程就可以干完了，而咱们设置的线程数据是10，事实上有5个线程是空闲的
        // 线程初始化可以改成Executors.newFixedThreadPool(5)
        splitList(list, 20).forEach(o -> {
            bufferInserts.add(new DoUrl(o));
        });

        executor.invokeAll(bufferInserts);
        executor.shutdown();
        stopWatch.stop();
        System.out.println("stopwatch:" + stopWatch.getTotalTimeMillis());
    }

    class DoUrl implements Callable<Integer> {
        List<String> url;

        public DoUrl(List<String> url) {
            this.url = url;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println("thread id:" + Thread.currentThread().getId());
            this.url.forEach(o -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            return 1;
        }
    }
}
