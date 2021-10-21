package com.lind.uaa.keycloak.permission;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限管理决断器
 * 判断用户拥有的权限或角色是否有资源访问权限
 *
 * @author Exrickx
 */
@Slf4j
public class MyAccessDecisionManager implements AccessDecisionManager {

    public static final String ROLE_PREFIX = "ROLE_";
    @Autowired
    PermissionService permissionService;
    /**
     * 当前资源服务器地址.
     */
    @Value("${keycloak.resource}")
    private String currentClientId;

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        log.info("path permission:{}", configAttributes);
        log.info("current user auth:{}", authentication.getAuthorities());
        if (configAttributes == null) {
            return;
        }
        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
        while (iterator.hasNext()) {
            ConfigAttribute c = iterator.next();
            String needPerm = c.getAttribute();
            needPerm = removeRolePrefix(needPerm);//比较时都去掉前缀
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                //获取当前用户token里的权限字段
                String userAuth = removeRolePrefix(ga.getAuthority());
                if (needPerm.equals(userAuth)) {
                    return;
                } else {
                    //通过角色取它的权限列表
                    List<ResourcePermission> permissionList = permissionService.getByRoleId(userAuth);
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

    /**
     * 角色格式化，对客户端角色进行加工.
     *
     * @param auth
     * @return
     */
    private String removeRolePrefix(String auth) {
        int index = auth.startsWith(ROLE_PREFIX) ? ROLE_PREFIX.length() : 0;
        auth = auth.substring(index);
        if (auth.startsWith(currentClientId)) {
            //currentClientId.length() + 1表示clientId/role里的/
            index = auth.startsWith(currentClientId) ? currentClientId.length() + 1 : 0;
            return auth.substring(index);
        }
        return auth;
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
