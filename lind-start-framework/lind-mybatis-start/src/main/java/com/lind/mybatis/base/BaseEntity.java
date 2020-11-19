package com.lind.mybatis.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lind.mybatis.util.SnowFlakeUtil;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

/**
 * mybatis实体基类.
 */
@Data
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键.
     * final保证了不会被其它实体override,例如由vo向entity赋值时,需要使用final来控制一下.
     */
    @TableId(type = IdType.INPUT)
    private final String id = String.valueOf(SnowFlakeUtil.getFlowIdInstance().nextId());

    /**
     * 建立人Id,需要实现AuditorAware接口.
     */
    @TableField("create_by")
    @CreatedBy
    private String createBy;

    /**
     * 建立时间.
     */
    @SuppressFBWarnings("EI_EXPOSE_REP")
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
    @SuppressFBWarnings("EI_EXPOSE_REP")
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
