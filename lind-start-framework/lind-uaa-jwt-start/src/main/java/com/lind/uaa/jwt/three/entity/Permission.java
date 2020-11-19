package com.lind.uaa.jwt.three.entity;

import com.lind.uaa.jwt.entity.ResourcePermission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements ResourcePermission {
    private String id;
    /**
     * 权限名称.
     */
    private String title;
    /**
     * 资源相对路径.
     */
    private String path;
    /**
     * 类型：0菜单,1按钮.
     */
    private Integer type;
    private String parentId;
    private ResourcePermission parent;
    private List<ResourcePermission> sons;
}
