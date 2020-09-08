package com.lind.mybatis.audit;

import java.util.Optional;

/**
 * 部门审计接口,实现类返回当前登陆人部门.
 *
 * @param <T>
 */
public interface DepartmentAuditorAware<T> {
    Optional<T> getCurrentAuditor();
}
