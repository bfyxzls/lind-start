package com.lind.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lind.mybatis.dao.UserDao;
import com.lind.mybatis.entity.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;


    @Override
    public BaseMapper<TUser> getRepository() {
        return userDao;
    }

    @Override
    public IPage<TUser> findByCondition(Pageable pageable) {
        QueryWrapper<TUser> userQueryWrapper = new QueryWrapper<>();
        return userDao.selectPage(
                new Page<>(pageable.getPageNumber(), pageable.getPageSize()),
                userQueryWrapper);
    }
}
