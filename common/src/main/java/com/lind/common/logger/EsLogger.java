package com.lind.common.logger;

public class EsLogger implements Logger {
    @Override
    public void info(String message) {
        System.out.println("es:" + message);
    }

    @Override
    public void info(String message, Object... obj) {
        System.out.format(message, obj);
    }
}
