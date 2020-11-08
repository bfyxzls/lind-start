package com.lind.uaa.keycloak.permission;

import java.util.List;

/**
 * 获取权限.
 */
public interface PermissionService {
    List<SourcePermission> getAll();
}
