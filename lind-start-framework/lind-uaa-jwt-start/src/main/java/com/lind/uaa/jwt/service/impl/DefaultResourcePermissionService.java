package com.lind.uaa.jwt.service.impl;

import com.lind.uaa.jwt.entity.ResourcePermission;
import com.lind.uaa.jwt.service.ResourcePermissionService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 默认的一个实例,避免没有实现时的报错,因为资源服务不需要实现它.
 */
@Service
@ConditionalOnMissingBean(ResourcePermissionService.class)
public class DefaultResourcePermissionService implements ResourcePermissionService {
    @Override
    public List<? extends ResourcePermission> getAll() {
        return null;
    }

    @Override
    public List<? extends ResourcePermission> getAllByType(Integer type) {
        return null;
    }

    @Override
    public List<? extends ResourcePermission> getByUserId(String userId) {
        return null;
    }
}
