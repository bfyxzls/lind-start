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
 * 默认的一个实例,避免没有实现时的报错,因为资源服务不需要实现它.
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
        if (redisService.hasKey(Constants.PERMISSION_ALL)) {
            log.info("从缓存读取permission-all数据");
            return new ObjectMapper().readValue(
                    redisService.get(Constants.PERMISSION_ALL).toString(),
                    new TypeReference<List<ResourcePermission>>() {
                    });
        }
        log.info("具体服务应该先实现ResourcePermissionService接口，其它服务从缓存读取");

        return null;
    }

    @SneakyThrows
    @Override
    public List<? extends ResourcePermission> getAllByRoleId(String roleId) {
        if (redisService.hasKey(Constants.ROLE_PERMISSION + "::" + roleId)) {
            log.info("从缓存读取role-permission数据");
            return new ObjectMapper().readValue(
                    redisService.get(Constants.ROLE_PERMISSION + "::" + roleId).toString(),
                    new TypeReference<List<ResourcePermission>>() {
                    });
        }
        return null;
    }
}
