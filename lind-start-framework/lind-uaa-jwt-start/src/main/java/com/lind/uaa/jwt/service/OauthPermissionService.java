package com.lind.uaa.jwt.service;

import com.lind.uaa.jwt.entity.ResourcePermission;

import java.util.List;

public interface OauthPermissionService {
    /**
     * 获取所有资源权限.
     *
     * @return
     */
    List<ResourcePermission> getAll();

    /**
     * 按着角色ID获取资源权限.
     *
     * @return
     */
    List<ResourcePermission> getByRoleId(String roleId);
}
