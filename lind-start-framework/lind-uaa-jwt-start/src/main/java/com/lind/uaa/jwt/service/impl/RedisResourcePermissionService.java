package com.lind.uaa.jwt.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lind.redis.service.RedisService;
import com.lind.uaa.jwt.config.Constants;
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

    @Override
    public Set<? extends ResourcePermission> getUserAll() {
        return null;
    }

    @SneakyThrows
    @Override
    public List<? extends ResourcePermission> getAll() {
        if (redisService.hasKey(Constants.PERMISSION_ALL)) {
            List<? extends ResourcePermission> resourcePermissions =
                    new ObjectMapper().readValue(redisService.get(Constants.PERMISSION_ALL).toString(),
                            new TypeReference<List<ResourcePermission>>() {
                            });

            return resourcePermissions;
        }
        return null;
    }

    @SneakyThrows
    @Override
    public List<? extends ResourcePermission> getAllByRoleId(String roleId) {
        String rolePermissionKey = Constants.ROLE_PERMISSION.concat(roleId);
        if (redisService.hasKey(rolePermissionKey)) {
            List<? extends ResourcePermission> permissionList =
                    new ObjectMapper().readValue(
                            redisService.get(Constants.ROLE_PERMISSION + roleId).toString(),
                            new TypeReference<List<ResourcePermission>>() {
                            });
            return permissionList;
        }
        return null;
    }
}
