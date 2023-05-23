package com.lind.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lind.auth.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lind
 * @date 2023/5/22 17:59
 * @since 1.0.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
