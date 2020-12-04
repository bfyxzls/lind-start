package com.lind.uaa.jwt.three.entity;


import lombok.Data;
import lombok.ToString;

/**
 * @author Exrick
 */
@Data
@ToString
public class RolePermission {
    private String id;
    private String roleId;
    private String permissionId;
    private Boolean selected =true;
}