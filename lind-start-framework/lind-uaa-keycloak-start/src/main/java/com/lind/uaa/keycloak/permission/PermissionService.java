package com.lind.uaa.keycloak.permission;

import java.util.List;

/**
 * 获取数据仓库里的权限.
 */
public interface PermissionService {
    /**
     * 获取资源与权限对应关系列表.
     *
     * @return
     */
    List<ResourcePermission> getAll();
}
