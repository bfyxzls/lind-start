package com.lind.kafka.producer;

import org.slf4j.MDC;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Map;

public class ListenableFutureCallbackWithTracing<T> implements ListenableFutureCallback<T> {
    protected final ListenableFutureCallback<T> origListenableFutureCallback;
    protected final Map<String, String> mdcContextMap;


    public ListenableFutureCallbackWithTracing(ListenableFutureCallback<T> origListenableFutureCallback, Map<String, String> mdcContextMap) {
        this.origListenableFutureCallback = origListenableFutureCallback;
        this.mdcContextMap = mdcContextMap;
    }


    @Override
    @SuppressWarnings("deprecation")
    public void onSuccess(T result) {


        System.out.println(mdcContextMap.get("traceId"));
        origListenableFutureCallback.onSuccess(result);

    }

    @Override
    @SuppressWarnings("deprecation")
    public void onFailure(Throwable ex) {
        try {
            System.out.println(mdcContextMap.get("traceId"));

            origListenableFutureCallback.onFailure(ex);
        } finally {
        }
    }
}
