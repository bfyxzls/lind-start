package com.lind.mybatis.audit;

import java.util.Optional;

/**
 * 当前登陆用户相关信息.
 *
 * @param <T> 任何类型.
 */
public interface UserAuditorAware<T> {
    Optional<T> getCurrentAuditor();
}
