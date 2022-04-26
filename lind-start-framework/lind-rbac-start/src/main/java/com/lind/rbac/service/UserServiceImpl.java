package com.lind.rbac.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lind.rbac.dao.RoleDao;
import com.lind.rbac.dao.UserDao;
import com.lind.rbac.dao.UserRoleDao;
import com.lind.rbac.entity.Role;
import com.lind.rbac.entity.User;
import com.lind.rbac.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserDetailsService {

  @Autowired
  UserDao userDao;
  @Autowired
  UserRoleDao userRoleDao;
  @Autowired
  RoleDao roleDao;

  /**
   * 2.由getAuthenticationManager().authenticate调用这个方法.
   *
   * @param username
   * @return
   * @throws UsernameNotFoundException
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    List<User> userDetails = userDao.selectList(new QueryWrapper<>());
    //这块需要将明文取出来后才能检索
    User user = userDetails.stream().filter(o -> o.getUsername().equals(username)).findFirst().orElse(null);
    if (user != null) {
      List<UserRole> userRoleList = userRoleDao.selectList(
          new QueryWrapper<UserRole>().lambda().eq(UserRole::getUserId, user.getId()));
      if (!CollectionUtils.isEmpty(userRoleList)) {
        List<String> roleIdList = userRoleList.stream().map(o -> o.getRoleId()).collect(Collectors.toList());
        List<Role> roleList = roleDao.selectList(
            new QueryWrapper<Role>().lambda().in(Role::getId, roleIdList));
        user.setResourceRoles(roleList);
      }
    }

    return user;
  }
}
