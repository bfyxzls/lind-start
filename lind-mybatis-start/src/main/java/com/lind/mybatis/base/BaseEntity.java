package com.lind.mybatis.base;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lind.mybatis.audit.CreatedDepartmentBy;
import com.lind.mybatis.util.SnowFlakeUtil;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Exrickx
 */
@Data
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键.
     */
    @TableId
    private String id = String.valueOf(SnowFlakeUtil.getFlowIdInstance().nextId());

    /**
     * 建立人Id,需要实现AuditorAware接口.
     */
    @TableField("create_by")
    @CreatedBy
    private String createBy;

    /**
     * 建立时间.
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @TableField("create_time")
    @CreatedDate
    private Date createTime;

    /**
     * 更新人ID,需要实现AuditorAware接口.
     */
    @TableField("update_by")
    @LastModifiedBy
    private String updateBy;

    /**
     * 更新时间.
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @TableField("update_time")
    @LastModifiedDate
    private Date updateTime;

    /**
     * 删除标记.
     */
    @TableField("del_flag")
    @TableLogic
    private Integer delFlag = 0;



}