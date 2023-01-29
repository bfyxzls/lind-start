package com.lind.logback.mdc;

import org.slf4j.MDC;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;

import static com.lind.logback.mdc.LogInterceptor.TRACE_ID;

/**
 * @author lind
 * @date 2023/1/29 15:18
 * @since 1.0.0
 */
public class ThreadMdcUtil
{
    public static void setTraceIdIfAbsent() {
        if (MDC.get(TRACE_ID) == null)
        {
            MDC.put(TRACE_ID, UUID.randomUUID().toString().replaceAll("-",""));
        }
    }
    public static <T> Callable<T> wrap(final Callable<T> callable, final Map<String, String> context) {
        return () -> {
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            setTraceIdIfAbsent();
            try {
                return callable.call();
            } finally {
                MDC.clear();
            }
        };
    }
    public static Runnable wrap(final Runnable runnable, final Map<String, String> context) {
        return () -> {
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            //设置traceId
            setTraceIdIfAbsent();
            try {
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }
}
