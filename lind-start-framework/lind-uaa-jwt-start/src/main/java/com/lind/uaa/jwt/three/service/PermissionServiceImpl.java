package com.lind.uaa.jwt.three.service;

import com.lind.uaa.jwt.entity.ResourcePermission;
import com.lind.uaa.jwt.service.ResourcePermissionService;
import com.lind.uaa.jwt.three.entity.Permission;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class PermissionServiceImpl implements ResourcePermissionService {
    static List<Permission> list = Arrays.asList(
            new Permission("1", "系统管理", "/admin/**", 0, null, null, null, null),
            new Permission("2", "文章列表", "/article/index*", 0, "1", null, null, null),
            new Permission("3", "添加文章", null, 1, "2", null, null, "sys:add"),
            new Permission("4", "删除文章", "/article/del*", 1, "2", null, null, null),
            new Permission("5", "信息管理", "/admin/info-mgr**", 0, "1", null, null, null)
    );

    @Override
    public Set<? extends ResourcePermission> getUserAll() {
        return null;
    }

    @Override
    public List<Permission> getAll() {
        return list;
    }

    @Override
    public List<? extends ResourcePermission> getAllByRoleId(String roleId) {
        return list;
    }
}
