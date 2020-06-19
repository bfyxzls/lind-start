package com.lind.common.logger;

public class ConsoleLogger implements Logger {
    @Override
    public void info(String message) {
        System.out.println("console:" + message);
    }

    @Override
    public void info(String message, Object... obj) {
        System.out.format(message, obj);
    }
}
