package com.lind.rbac.entity;


import lombok.Data;
import lombok.ToString;

/**
 * 角色权限表.
 */
@Data
@ToString
public class RolePermission {
    private String id;
    private String roleId;
    private String permissionId;
    private Boolean selected =true;
}