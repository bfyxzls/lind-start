package com.lind.mybatis.base;

import com.baomidou.mybatisplus.annotation.TableField;
import com.lind.mybatis.audit.CreatedDepartmentBy;
import lombok.Data;

@Data
public abstract class AuditDepartment extends BaseEntity {
    /**
     * 建立部门.
     */
    @TableField("create_department_id")
    @CreatedDepartmentBy
    private String createDepartmentId;
}
