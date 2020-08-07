package com.lind.uaa.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lind.uaa.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {
}
