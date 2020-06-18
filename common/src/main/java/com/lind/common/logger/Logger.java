package com.lind.common.logger;

public interface Logger {
    void info(String message);

    void info(String message, Object... obj);
}
