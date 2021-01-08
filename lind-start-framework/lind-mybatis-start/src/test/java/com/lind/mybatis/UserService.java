package com.lind.mybatis;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lind.mybatis.base.BaseService;
import com.lind.mybatis.entity.TUser;
import org.springframework.data.domain.Pageable;

/**
 * 个性化业务.
 */
public interface UserService extends BaseService<TUser> {
    IPage<TUser> findByCondition(Pageable pageable);
}
