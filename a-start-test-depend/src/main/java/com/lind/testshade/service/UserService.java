package com.lind.testshade.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lind.testshade.entity.UserInfo;
import com.lind.testshade.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {
    @Autowired
    UserMapper userMapper;

    public List<UserInfo> getByName(String name) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", name);
        return userMapper.selectList(queryWrapper);
    }
}
