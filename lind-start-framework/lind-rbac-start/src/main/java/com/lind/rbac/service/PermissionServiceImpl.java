package com.lind.rbac.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.lind.rbac.dao.PermissionDao;
import com.lind.rbac.dao.RolePermissionDao;
import com.lind.rbac.entity.Permission;
import com.lind.rbac.entity.RolePermission;
import com.lind.uaa.jwt.entity.ResourcePermission;
import com.lind.uaa.jwt.service.ResourcePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements ResourcePermissionService {
  @Autowired
  PermissionDao permissionDao;
  @Autowired
  RolePermissionDao rolePermissionDao;

  @Override
  public List<Permission> getAll() {
    return permissionDao.selectList(new QueryWrapper<>());
  }

  @Override
  public List<? extends ResourcePermission> getAllByRoleId(String roleId) {
    QueryWrapper<RolePermission> queryWrapper = new QueryWrapper<>();
    queryWrapper.lambda().eq(RolePermission::getRoleId, roleId);
    List<RolePermission> list = rolePermissionDao.selectList(queryWrapper);
    if (CollectionUtils.isNotEmpty(list)) {
      List<String> permissionIdList = list.stream().map(o -> o.getPermissionId()).collect(Collectors.toList());
      QueryWrapper<Permission> permissionQueryWrapper = new QueryWrapper<>();
      permissionQueryWrapper.lambda().in(Permission::getId, permissionIdList);
      return permissionDao.selectList(permissionQueryWrapper);
    }
    return null;
  }
}
