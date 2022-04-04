package com.lind.uaa.jwt.service.impl;

import com.lind.redis.service.RedisService;
import com.lind.uaa.jwt.config.Constants;
import com.lind.uaa.jwt.config.SecurityUtil;
import com.lind.uaa.jwt.entity.ResourcePermission;
import com.lind.uaa.jwt.service.ResourcePermissionService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * 从Redis中读取权限,这个是为非授权服务提供的默认实现.
 * 理解：在微服务设计中，你会有授权服务，业务服务等，而授权服务或者用户服务，它会有对用户库的访问权限，而其它服务不需要的，
 * 直接从redis中获取即可，它不需要实现ResourcePermissionService的实现。
 */
@Slf4j
public class RedisResourcePermissionService implements ResourcePermissionService {
    @Autowired
    RedisService redisService;
    @Autowired
    SecurityUtil securityUtil;

    @SneakyThrows
    @Override
    public Set<? extends ResourcePermission> getUserAll() {
        String key = Constants.USER_PERMISSION + securityUtil.getCurrUser().getId();
        if (redisService.hasKey(key)) {
            Set<? extends ResourcePermission> resourcePermissions = (Set<ResourcePermission>) redisService.get(key);
            return resourcePermissions;
        }
        return null;
    }

    @SneakyThrows
    @Override
    public List<? extends ResourcePermission> getAll() {
        if (redisService.hasKey(Constants.PERMISSION_ALL)) {
            return (List<? extends ResourcePermission>) redisService.get(Constants.PERMISSION_ALL);
        }
        return null;
    }

    @SneakyThrows
    @Override
    public List<? extends ResourcePermission> getAllByRoleId(String roleId) {
        String rolePermissionKey = Constants.ROLE_PERMISSION.concat(roleId);
        if (redisService.hasKey(rolePermissionKey)) {
            return (List<? extends ResourcePermission>) redisService.get(Constants.ROLE_PERMISSION + roleId);
        }
        return null;
    }
}
