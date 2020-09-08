package com.lind.uaa.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lind.mybatis.base.BaseEntity;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 用户.
 */
@Data
@ToString
@TableName("t_user")
public class User extends BaseEntity {

    /**
     * 用户名.
     */
    private String username;

    /**
     * 密码.
     */
    private String password;

    /**
     * 用户头像.
     */
    private String avatar = "";

    /**
     * 状态 默认0正常 -1拉黑.
     */
    private Integer status = 0;

    /**
     * 所属部门id.
     */
    private String departmentId;

    /**
     * 用户拥有角色.
     */
    @TableField(exist = false)
    private List<Role> roles;

    /**
     * 用户拥有的权限.
     */
    @TableField(exist = false)
    private List<Permission> permissions;

    /**
     * 自定义数据权限-部门ID.
     */
    @TableField(exist = false)
    private List<String> customDepartmentIds;
}
