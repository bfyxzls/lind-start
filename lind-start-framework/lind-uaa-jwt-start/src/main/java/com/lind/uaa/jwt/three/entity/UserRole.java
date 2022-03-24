package com.lind.uaa.jwt.three.entity;

import com.lind.common.util.SnowFlakeUtils;
import lombok.Data;

/**
 * 用户与角色关系表
 */
@Data
public class UserRole {
    private static final long serialVersionUID = 1L;
    private String id= SnowFlakeUtils.getFlowIdInstance().toString();
    private String userId;
    private String roleId;
    private String roleName;
}
