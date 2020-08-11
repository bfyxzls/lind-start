package com.lind.uaa.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.lind.uaa.entity.*;
import com.lind.uaa.mapper.*;
import com.lind.uaa.service.OauthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements OauthUserService {
    @Autowired
    UserDao userDao;
    @Autowired
    PermissionDao permissionDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    RolePermissionDao rolePermissionDao;
    @Autowired
    UserRoleDao userRoleDao;

    @Override
    public User getByUserName(String username) {
        User user = userDao.selectOne(new QueryWrapper<User>().lambda().eq(User::getUsername, username));
        if (user != null) {
            List<UserRole> userRoleList = userRoleDao.selectList(new QueryWrapper<UserRole>().lambda().eq(UserRole::getUserId, user.getId()));
            if (CollectionUtils.isNotEmpty(userRoleList)) {
                List<String> roleIds = userRoleList.stream().map(o -> o.getRoleId()).collect(Collectors.toList());
                List<Role> roleList = roleDao.selectList(new QueryWrapper<Role>().lambda().in(Role::getId, roleIds));
                user.setRoles(roleList);
                List<RolePermission> rolePermissionList = rolePermissionDao.selectList(
                        new QueryWrapper<RolePermission>().lambda().in(RolePermission::getRoleId, roleIds));
                if (CollectionUtils.isNotEmpty(rolePermissionList)) {
                    List<String> permissionIds = rolePermissionList.stream().map(o -> o.getPermissionId()).collect(Collectors.toList());
                    List<Permission> permissionList = permissionDao.selectList(
                            new QueryWrapper<Permission>().lambda().in(Permission::getId, permissionIds));
                    user.setPermissions(permissionList);
                }
            }
        }
        return user;
    }
}
