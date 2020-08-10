package com.lind.uaa.service;

import com.lind.uaa.entity.Permission;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OauthPermissionService {
    List<Permission> getByType(int type);
}
