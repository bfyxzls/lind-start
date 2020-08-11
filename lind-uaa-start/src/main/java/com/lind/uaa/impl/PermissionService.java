package com.lind.uaa.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lind.uaa.entity.Permission;
import com.lind.uaa.mapper.PermissionDao;
import com.lind.uaa.service.OauthPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService implements OauthPermissionService {
    @Autowired
    PermissionDao permissionDao;

    @Override
    public List<Permission> getByType(int type) {
        List<Permission> permissions = permissionDao.selectList(new QueryWrapper<Permission>().lambda().eq(Permission::getType, type));
        return permissions;
    }

}
