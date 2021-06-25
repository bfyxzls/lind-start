package com.lind.kafka.util;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * 重复执行类
 */
public class RetryUtils {
    @SneakyThrows
    public static void reDo(int errorRetry, Runnable runnable) {
        int retry = 0;
        while (retry++ < errorRetry) {
            try {
                runnable.run();
            } catch (Exception e) {
                TimeUnit.MILLISECONDS.sleep(1000);
                System.err.println(e);
            }
        }
        System.out.println("reDo success");
    }
}
