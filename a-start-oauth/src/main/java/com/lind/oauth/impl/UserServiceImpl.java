package com.lind.oauth.impl;

import com.lind.uaa.entity.User;
import com.lind.uaa.service.OauthUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements OauthUserService {
    @Override
    public User getByUserName(String username) {
        com.lind.oauth.entity.User user = new com.lind.oauth.entity.User();
        user.setId("1");
        user.setUsername("test");
        user.setPassword(new BCryptPasswordEncoder().encode("123456"));
        return user;
    }
}
