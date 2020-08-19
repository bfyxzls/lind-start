package com.lind.common.lock;

/**
 * @author 赵阳
 * @Description TODO
 * @date 2020/7/5 22:59
 **/
public class RepeatSubmitException extends RuntimeException {

    public RepeatSubmitException() {
    }

    public RepeatSubmitException(String message) {
        super(message);
    }

    public RepeatSubmitException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepeatSubmitException(Throwable cause) {
        super(cause);
    }

    public RepeatSubmitException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
