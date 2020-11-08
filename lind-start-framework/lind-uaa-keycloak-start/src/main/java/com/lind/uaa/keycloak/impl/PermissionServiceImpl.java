package com.lind.uaa.keycloak.impl;

import com.lind.permission.PermissionService;
import com.lind.permission.SourcePermission;
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
