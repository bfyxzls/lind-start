package com.lind.common.lock.exception;

public class RepeatSubmitException extends IllegalArgumentException {

    public RepeatSubmitException(String message) {
        super(message);
    }

}
