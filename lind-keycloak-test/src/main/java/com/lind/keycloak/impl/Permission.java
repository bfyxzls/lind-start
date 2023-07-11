package com.lind.keycloak.impl;

import com.lind.uaa.keycloak.permission.ResourcePermission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Exrick
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements ResourcePermission {

    /**
     * 菜单标题.
     */
    private String title;
    /**
     * 页面路径/资源链接url.
     */
    private String path;


}
