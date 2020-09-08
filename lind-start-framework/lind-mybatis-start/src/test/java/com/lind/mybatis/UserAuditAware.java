package com.lind.mybatis;

import com.lind.mybatis.audit.UserAuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 获取当前登陆的用户信息.
 */
@Component
public class UserAuditAware implements UserAuditorAware {
    @Override
    public Optional getCurrentAuditor() {
        return Optional.of("1");
    }
}
