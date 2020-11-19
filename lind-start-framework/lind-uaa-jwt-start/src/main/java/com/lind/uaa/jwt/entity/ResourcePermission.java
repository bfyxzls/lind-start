package com.lind.uaa.jwt.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 权限.
 */
public interface ResourcePermission extends Serializable {
    String getId();

    /**
     * 权限名称.
     */
    String getTitle();

    /**
     * 页面路径/资源url.
     */
    String getPath();

    /**
     * 上级权限Id.
     *
     * @return
     */
    String getParentId();

    /**
     * 权限类型，0菜单，1按钮.
     *
     * @return
     */
    Integer getType();

    ResourcePermission getParent();

    void setParent(ResourcePermission resourcePermission);

    List<ResourcePermission> getSons();

    void setSons(List<ResourcePermission> resourcePermissions);
}