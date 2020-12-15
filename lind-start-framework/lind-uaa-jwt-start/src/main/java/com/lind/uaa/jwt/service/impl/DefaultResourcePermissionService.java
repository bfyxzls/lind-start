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
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 这个是为非授权服务提供的默认实现，不做任务事.
 */
@Service
@Slf4j
@ConditionalOnMissingBean(ResourcePermissionService.class)
public class DefaultResourcePermissionService implements ResourcePermissionService {
    @Autowired
    RedisService redisService;

    @SneakyThrows
    @Override
    public List<? extends ResourcePermission> getAll() {
        List<? extends ResourcePermission> resourcePermissions =
                new ObjectMapper().readValue(redisService.get(Constants.PERMISSION_ALL).toString(),
                        new TypeReference<List<ResourcePermission>>() {
                        });

        return resourcePermissions;
    }

    @SneakyThrows
    @Override
    public List<? extends ResourcePermission> getAllByRoleId(String roleId) {
        List<? extends ResourcePermission> permissionList =
                new ObjectMapper().readValue(
                        redisService.get(Constants.ROLE_PERMISSION + roleId).toString(),
                        new TypeReference<List<ResourcePermission>>() {
                        });
        return permissionList;
    }
}
