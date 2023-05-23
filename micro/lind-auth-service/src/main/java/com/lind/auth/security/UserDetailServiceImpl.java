package com.lind.auth.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lind.auth.mapper.UserMapper;
import com.lind.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author lind
 * @date 2023/5/22 17:54
 * @since 1.0.0
 */
@Service
@Primary
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //获取本地用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(User::getUserName, userName);
        User user = userMapper.selectOne(wrapper);
        if(user != null){
            //返回oauth2的用户
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getPassword(),
                    AuthorityUtils.createAuthorityList(user.getRole())) ;
        }else{
            throw  new UsernameNotFoundException("用户["+userName+"]不存在");
        }
    }
}
