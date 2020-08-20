package com.lind.common.lock.exception;

public class RedisUserManualLockException extends IllegalArgumentException {
    public RedisUserManualLockException(String s) {
        super(s);
    }
}
