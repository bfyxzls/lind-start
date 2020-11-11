package com.lind.uaa.keycloak.permission;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * 权限管理决断器
 * 判断用户拥有的权限或角色是否有资源访问权限
 *
 * @author Exrickx
 */
@Slf4j
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {

    public static final String ROLE_PREFIX = "ROLE_";

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {

        if (configAttributes == null) {
            return;
        }
        log.info("configAttributes:{}", configAttributes);
        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
        int devote = 0;
        while (iterator.hasNext()) {
            ConfigAttribute c = iterator.next();
            String needPerm = c.getAttribute();
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (!needPerm.startsWith(ROLE_PREFIX)) {
                    needPerm = ROLE_PREFIX.concat(needPerm);
                }
                if (needPerm.trim().equals(ga.getAuthority())) {
                    devote++;
                }
            }
        }
        // 用户拥有的权限与资源需要的所有权限都匹配,才能访问资源.
        if (devote == configAttributes.size()) {
            return;
        }
        throw new AccessDeniedException("抱歉，您没有访问权限");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
