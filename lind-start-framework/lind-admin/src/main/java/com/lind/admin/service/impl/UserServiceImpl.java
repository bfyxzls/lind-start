package com.lind.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lind.admin.dao.UserDao;
import com.lind.admin.entity.User;
import com.lind.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lind
 * @date 2022/11/15 8:55
 * @since 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public List<User> pageList(int offset, int pagesize, String username, int role) {
        return userDao.selectList(new QueryWrapper<User>().lambda().eq(User::getUsername, username).eq(User::getRole, role)).stream().skip(offset).collect(Collectors.toList());
    }

    @Override
    public long pageListCount(int offset, int pagesize, String username, int role) {
        return userDao.selectCount(new QueryWrapper<User>().lambda().eq(User::getUsername, username).eq(User::getRole, role));
    }

    @Override
    public User loadByUserName(String username) {
        return userDao.selectOne(new QueryWrapper<User>().lambda().eq(User::getUsername, username));
    }

    @Override
    public int save(User user) {
        return userDao.insert(user);
    }

    @Override
    public int update(User user) {
        return userDao.updateById(user);
    }

    @Override
    public int delete(int id) {
        return userDao.deleteById(id);
    }
}
