package com.lind.uaa.jwt.entity;

import java.io.Serializable;

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
}