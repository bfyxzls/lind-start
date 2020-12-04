package com.lind.keycloak.client.impl;

import com.lind.uaa.keycloak.permission.PermissionService;
import com.lind.uaa.keycloak.permission.ResourcePermission;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {


    @Override
    public List<ResourcePermission> getAll() {
        return Arrays.asList(
                new Permission("商品管理", "/products"),
                new Permission("首页", "/index")
        );
    }

    @Override
    public List<ResourcePermission> getByRoleId(String roleKey) {
        if (roleKey.equals("商品管理员"))
            return Arrays.asList(
                    new Permission("商品管理", "/products"),
                    new Permission("商品添加", "/products/add"),
                    new Permission("商品删除", "/products/del")
            );
        else
            return null;
    }

}
