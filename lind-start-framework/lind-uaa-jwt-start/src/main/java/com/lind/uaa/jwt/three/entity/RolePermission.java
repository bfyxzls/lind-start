package com.lind.uaa.jwt.three.entity;


import com.lind.common.util.SnowFlakeUtils;
import lombok.Data;
import lombok.ToString;

/**
 * 角色权限（菜单）关系表.
 */
@Data
@ToString
public class RolePermission {
    private String id= SnowFlakeUtils.getFlowIdInstance().toString();
    private String roleId;
    private String permissionId;
    private Boolean selected =true;
}