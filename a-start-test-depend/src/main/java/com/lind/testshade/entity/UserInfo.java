package com.lind.testshade.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_user")
public class UserInfo {
    @TableField("username")
    private String name;
}
