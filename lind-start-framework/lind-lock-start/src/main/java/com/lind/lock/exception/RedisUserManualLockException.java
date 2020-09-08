package com.lind.lock.exception;

/**
 * 手动锁资源异常.
 */
public class RedisUserManualLockException extends IllegalArgumentException {
    public RedisUserManualLockException(String s) {
        super(s);
    }
}
