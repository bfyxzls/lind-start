package com.lind.uaa.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lind.mybatis.base.BaseEntity;
import lombok.Data;

/**
 * @author Exrick
 */
@Data
@TableName("t_role_permission")
public class RolePermission extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private String roleId;
    private String permissionId;
}