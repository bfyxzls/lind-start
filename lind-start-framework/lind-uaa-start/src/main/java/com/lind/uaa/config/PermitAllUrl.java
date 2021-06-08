package com.lind.uaa.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 需要放开权限的url
 */
public final class PermitAllUrl {

    /**
     * 监控中心和swagger需要访问的url
     */
    static final List<String> ENDPOINTS = Arrays.asList("/actuator/**",
            "/v2/api-docs/**",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/webjars/**",
            "/webjars/**",
            "/oauth/token",
            "/oauth/test");

    /**
     * 需要放开权限的url
     *
     * @param urls 自定义的url
     * @return 自定义的url和监控中心需要访问的url集合
     */
    public static String[] permitAllUrl(String... urls) {
        if (urls == null || urls.length == 0) {
            return ENDPOINTS.toArray(new String[ENDPOINTS.size()]);
        }

        Set<String> set = new HashSet<>();
        set.addAll(ENDPOINTS);
        Collections.addAll(set, urls);

        return set.toArray(new String[set.size()]);
    }

}
