package com.lind.uaa.keycloak.permission;

/**
 * 资源权限.
 */
public interface SourcePermission {
    /**
     * 菜单标题.
     */
    String getTitle();

    /**
     * 页面路径/资源链接url.
     */
    String getPath();

    /**
     * 权限名称
     */
    String getAuth();

}
