package com.lind.keycloak.impl;


import com.lind.uaa.keycloak.permission.PermissionService;
import com.lind.uaa.keycloak.permission.ResourcePermission;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    /**
     * 被保存的api资源.
     *
     * @return
     */
    @Override
    public List<ResourcePermission> getAll() {
        return Arrays.asList(
                new Permission("商品管理", "/products"),
                new Permission("用户管理", "/users"),
                new Permission("商品删除", "/data/sifa*"),
                new Permission("律所实务", "/data/lvsuo*"),
                new Permission("法学期刊", "/data/faxue*")
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
        else if (roleKey.equals("司法案例")) {
            return Arrays.asList(
                    new Permission("商品删除", "/data/sifa*")
            );
        } else if (roleKey.equals("律所实务")) {
            return Arrays.asList(
                    new Permission("律所实务", "/data/lvsuo*")
            );
        } else if (roleKey.equals("法学期刊")) {
            return Arrays.asList(
                    new Permission("法学期刊", "/data/faxue*")
            );
        }
        return null;
    }

}
