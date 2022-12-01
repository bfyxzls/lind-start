package com.lind.admin.util;

public class PermissionLimitException extends RuntimeException {
    public PermissionLimitException(String message) {
        super(message);
    }
}
