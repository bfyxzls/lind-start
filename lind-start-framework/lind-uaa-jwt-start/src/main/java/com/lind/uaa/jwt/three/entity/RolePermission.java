package com.lind.uaa.jwt.three.entity;


import lombok.Data;

/**
 * @author Exrick
 */
@Data
public class RolePermission {
    private String id;
    private String roleId;
    private String permissionId;
    private Boolean selected =true;
}