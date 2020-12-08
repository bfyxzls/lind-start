package com.lind.uaa.jwt.permission;

import com.lind.uaa.jwt.entity.ResourcePermission;
import com.lind.uaa.jwt.service.ResourcePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限资源管理器
 * 为权限决断器提供支持
 *
 * @author lind
 */
@Slf4j
@Component
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {


    @Autowired
    private ResourcePermissionService oauthPermissionService;

    private Map<String, Collection<ConfigAttribute>> map = null;

    /**
     * 加载权限表中所有操作请求权限
     */
    public void loadResourceDefine() {

        map = new HashMap<>(16);
        Collection<ConfigAttribute> configAttributes;
        ConfigAttribute cfg;
        // 获取启用的权限操作请求
        List<? extends ResourcePermission> resourcePermissions = oauthPermissionService.getAll();
        if (resourcePermissions != null) {
            for (ResourcePermission resourcePermission : resourcePermissions) {
                if (StringUtils.isNotBlank(resourcePermission.getTitle())
                        && StringUtils.isNotBlank(resourcePermission.getPath())) {
                    configAttributes = new ArrayList<>();
                    cfg = new SecurityConfig(resourcePermission.getTitle());//权限名称就是权限表里的title字段
                    configAttributes.add(cfg);
                    map.put(resourcePermission.getPath(), configAttributes);
                }
            }
        }
    }

    /**
     * 判定用户请求的url是否在权限表中
     * 如果在权限表中，则返回给decide方法，用来判定用户是否有此URL对应的权限
     * 如果不在权限表中则放行
     *
     * @param o
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {

        if (map == null) {
            loadResourceDefine();
        }
        //Object中包含用户请求request
        String url = ((FilterInvocation) o).getRequestUrl();
        PathMatcher pathMatcher = new AntPathMatcher();
        for (Map.Entry<String, Collection<ConfigAttribute>> item : map.entrySet()) {
            String resURL = item.getKey();
            if (StringUtils.isNotBlank(resURL) && pathMatcher.match(resURL, url)) {
                return item.getValue();
            }
        }

        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}