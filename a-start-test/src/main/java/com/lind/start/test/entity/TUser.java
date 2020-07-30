package com.lind.start.test.dto;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@TableName("t_user")
@Data
public class TUser {
    private static final long serialVersionUID = 1L;

    @TableField("username")
    private String username;

    private String password;


}