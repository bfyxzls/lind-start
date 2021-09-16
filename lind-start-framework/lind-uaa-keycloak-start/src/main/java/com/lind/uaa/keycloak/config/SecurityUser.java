package com.lind.uaa.keycloak.config;

import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
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


    /**
     * Principal.
     * @return
     */
    public static Object getCurrentPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.isNull(authentication)) {
            throw new IllegalArgumentException("401");
        }
        return authentication.getPrincipal();
    }

    /**
     * token.
     * @return
     */
    public static Object getCurrentToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((SimpleKeycloakAccount) ((KeycloakAuthenticationToken) authentication).getDetails())
                .getKeycloakSecurityContext()
                .getToken();
    }

    /**
     * 返回当前token里包含的scope.
     *
     * @return
     */
    public static String[] getScope() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String scopeString = ((SimpleKeycloakAccount) ((KeycloakAuthenticationToken) authentication).getDetails())
                .getKeycloakSecurityContext()
                .getToken()
                .getScope();
        return scopeString.split(" ");
    }
}
