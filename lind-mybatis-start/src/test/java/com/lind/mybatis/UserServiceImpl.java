package com.lind.mybatis;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lind.mybatis.dao.UserDao;
import com.lind.mybatis.entity.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;


    @Override
    public BaseMapper<TUser> getRepository() {
        return userDao;
    }
}
