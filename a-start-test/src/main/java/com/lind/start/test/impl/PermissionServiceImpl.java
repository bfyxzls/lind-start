package com.lind.start.test.impl;

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
                new Permission("chanpin", "/products", "read"),
                new Permission("首页", "/index", "read")
                );
    }

}
