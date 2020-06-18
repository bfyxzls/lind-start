package com.lind.common.logger;

import org.springframework.stereotype.Component;

public class ConsoleLogger implements Logger {
    @Override
    public void info(String message) {
        System.out.println(message);
    }

    @Override
    public void info(String message, Object... obj) {
        System.out.format(message, obj);
    }
}
