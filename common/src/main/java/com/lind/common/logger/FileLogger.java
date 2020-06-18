package com.lind.common.logger;

public class FileLogger implements Logger {
    @Override
    public void info(String message) {
        System.out.println("file:" + message);
    }

    @Override
    public void info(String message, Object... obj) {
        System.out.format("file:" + message, obj);
    }
}
