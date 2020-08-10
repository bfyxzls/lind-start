package com.lind.oauth.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lind.oauth.dao.UserDao;
import com.lind.uaa.service.OauthUserService;
import com.lind.uaa.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements OauthUserService {
@Autowired
    UserDao userDao;
    @Override
    public User getByUserName(String username) {
        return userDao.selectOne(new QueryWrapper<User>().lambda().eq(User::getUsername,username));
    }
}
