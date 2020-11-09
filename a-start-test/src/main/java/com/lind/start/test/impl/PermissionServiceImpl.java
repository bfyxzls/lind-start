package com.lind.start.test.impl;

import com.lind.uaa.keycloak.permission.PermissionService;
import com.lind.uaa.keycloak.permission.SourcePermission;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {


    @Override
    public List<SourcePermission> getAll() {
        return Arrays.asList(new Permission("首页", "/index", "address"));
    }

}
