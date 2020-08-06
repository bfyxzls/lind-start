package com.lind.mybatis;

import com.lind.mybatis.audit.DepartmentAuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 获取当前登录的部门信息.
 */
@Component
public class DepartmentAuditAware implements DepartmentAuditorAware {
    @Override
    public Optional getCurrentAuditor() {
        return Optional.of("1010");
    }
}
