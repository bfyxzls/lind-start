package com.lind.kafka.util;

import lombok.SneakyThrows;
import org.springframework.kafka.support.Acknowledgment;

/**
 * 重复执行类
 */
public class RetryUtils {
    @SneakyThrows
    public static void reDo(int errorRetry, Acknowledgment ack, Runnable runnable) {
        int retry = 0;
        while (retry++ < errorRetry) {
            try {
                runnable.run();
                ack.acknowledge();
            } catch (Exception e) {
                ack.nack(1000);
                System.err.println(e);
            }
        }
        System.out.println("reDo success");
    }
}
