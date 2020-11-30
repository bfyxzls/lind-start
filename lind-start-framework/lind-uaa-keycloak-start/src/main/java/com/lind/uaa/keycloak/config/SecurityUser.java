package com.lind.uaa.keycloak.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class SecurityUser {

    /**
     * 获取当前用户名
     *
     * @return
     */
    public static String getCurrentUserName() {
        Object principal = getCurrentPrincipal();

        return (String) principal;
    }


    public static Object getCurrentPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.isNull(authentication)) {
           throw new IllegalArgumentException("401");
        }
        return authentication.getPrincipal();
    }
}
