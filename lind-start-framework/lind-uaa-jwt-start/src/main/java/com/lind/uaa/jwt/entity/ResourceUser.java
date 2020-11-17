package com.lind.uaa.jwt.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 用户.
 */
public interface ResourceUser extends Serializable {
    String getId();

    /**
     * 用户名.
     */
    String getUsername();

    /**
     * 密码.
     *
     * @return
     */
    String getPassword();

    /**
     * 当前用户的权限.
     *
     * @return
     */
    List<ResourcePermission> getResourcePermissions();
}
