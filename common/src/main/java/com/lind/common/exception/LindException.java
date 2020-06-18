package com.lind.common.exception;

import lombok.Getter;

@Getter
public class LindException extends RuntimeException {
    private String msg;
    private Throwable e;

    public LindException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public LindException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.e = e;
    }
}
