package com.lind.oauth.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lind.oauth.dao.PermissionDao;
import com.lind.uaa.service.OauthPermissionService;
import com.lind.uaa.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService implements OauthPermissionService {
    @Autowired
    PermissionDao permissionDao;

    @Override
    public List<Permission> getByType(int type) {
        return permissionDao.selectList(new QueryWrapper<Permission>().lambda().eq(Permission::getType, type));
    }
}
