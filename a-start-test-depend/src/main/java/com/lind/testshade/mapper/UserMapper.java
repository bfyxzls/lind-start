package com.lind.testshade.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lind.testshade.entity.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<UserInfo> {
}
