package com.lind.uaa.service;

import com.lind.uaa.entity.Permission;

import java.util.List;

public interface OauthPermissionService {
    List<Permission> getByType(int type);
}
