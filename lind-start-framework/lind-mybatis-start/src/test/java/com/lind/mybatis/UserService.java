package com.lind.mybatis;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lind.common.dto.PageData;
import com.lind.mybatis.entity.TUser;
import com.lind.mybatis.service.BaseService;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * 个性化业务.
 */
public interface UserService extends BaseService<TUser> {
    IPage<TUser> findByCondition(Pageable pageable);
    PageData<TUser> page(Map<String, Object> params);
}
