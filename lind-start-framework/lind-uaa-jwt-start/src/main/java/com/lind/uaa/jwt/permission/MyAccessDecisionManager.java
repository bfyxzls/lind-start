package com.lind.uaa.jwt.permission;

import com.lind.uaa.jwt.entity.ResourcePermission;
import com.lind.uaa.jwt.service.ResourcePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限管理决断器
 * 判断用户拥有的权限或角色是否有资源访问权限
 * configAttributes表示当前资源需要的权限,authentication.getAuthorities()表示当前用户所拥有的权限
 */
@Slf4j
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {

    @Autowired
    ResourcePermissionService resourcePermissionService;

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        log.info("path permission:{}", configAttributes);
        log.info("current user auth:{}", authentication.getAuthorities());
        if (configAttributes == null) {
            return;
        }
        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
        while (iterator.hasNext()) {
            ConfigAttribute c = iterator.next();
            String needPerm = c.getAttribute();
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                // 匹配用户拥有的ga 和 系统中的needPerm
                String userAuth = ga.getAuthority();

                if (needPerm.trim().equals(userAuth)) {
                    return;
                } else {
                    //通过角色取它的权限列表
                    List<? extends ResourcePermission> permissionList = resourcePermissionService.getAllByRoleId(userAuth);
                    if (!CollectionUtils.isEmpty(permissionList)) {
                        List<String> authTitles = permissionList.stream().map(permission -> permission.getTitle()).collect(Collectors.toList());
                        if (authTitles.contains(needPerm)) {
                            return;
                        }
                    }
                }
            }
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
