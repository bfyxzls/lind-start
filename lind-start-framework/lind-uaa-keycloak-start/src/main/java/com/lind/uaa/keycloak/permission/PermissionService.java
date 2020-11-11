package com.lind.uaa.keycloak.permission;

import java.util.List;

/**
 * 获取权限.
 */
public interface PermissionService {
    /**
     * 获取资源与权限对应关系列表.
     *
     * @return
     */
    List<SourcePermission> getAll();
}
