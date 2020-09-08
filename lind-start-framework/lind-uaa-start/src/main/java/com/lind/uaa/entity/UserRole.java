package com.lind.uaa.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lind.mybatis.base.BaseEntity;
import lombok.Data;

/**
 * @author Exrickx
 */
@Data
@TableName("t_user_role")
public class UserRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String roleId;

    @TableField(exist = false)
    private String roleName;
}
