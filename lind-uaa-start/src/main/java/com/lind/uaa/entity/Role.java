package com.lind.uaa.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lind.mybatis.base.BaseEntity;
import lombok.Data;


/**
 * 角色.
 */
@Data
@TableName("t_role")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private Boolean defaultRole;

    private String dataType;

    private String description;

}
