package com.lind.testshade.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("t_user")
@Data
public class UserInfo {
    /**
     * 主键.
     */
    @TableId
    private String id = String.valueOf(SnowFlakeUtil.getFlowIdInstance().nextId());
    @TableField("username")
    private String username;
}
