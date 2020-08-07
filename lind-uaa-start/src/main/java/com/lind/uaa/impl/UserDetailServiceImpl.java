package com.lind.uaa.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lind.uaa.dao.UserDao;
import com.lind.uaa.entity.User;
import com.lind.uaa.redis.RedisUtil;
import com.lind.uaa.util.UAAConstant;
import com.lind.uaa.util.UAAException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    UserDao userDao;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String flagKey = "loginFailFlag:" + username;
        String value = redisTemplate.opsForValue().get(flagKey);
        Long timeRest = redisTemplate.getExpire(flagKey, TimeUnit.MINUTES);
        if (StringUtils.isNotBlank(value)) {
            //超过限制次数
            System.out.println("登录错误次数超过限制，请" + timeRest + "分钟后再试");
            throw new UAAException("登录错误次数超过限制，请" + timeRest + "分钟后再试");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUsername, username);
        User user = userDao.selectOne(queryWrapper);
        if (user != null) {
            //持久化到redis
            redisUtil.set(UAAConstant.USER + username, user);
        }
        return user;
    }
}
