package com.lind.uaa.jwt.entity;

import java.io.Serializable;

/**
 * 权限.
 */
public interface ResourcePermission extends Serializable {
    String getId();

    /**
     * 标题.
     */
    String getTitle();

    /**
     * 页面路径/资源链接url.
     */
    String getPath();

    /**
     * 资源允许的权限
     */
    String getAuth();

    /**
     * 资源允许的scope
     */
    String getScope();

}