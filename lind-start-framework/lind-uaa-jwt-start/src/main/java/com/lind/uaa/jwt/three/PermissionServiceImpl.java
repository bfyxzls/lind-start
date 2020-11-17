package com.lind.uaa.jwt.three;

import com.lind.uaa.jwt.entity.ResourcePermission;
import com.lind.uaa.jwt.service.OauthPermissionService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PermissionServiceImpl implements OauthPermissionService {
    @Override
    public List<ResourcePermission> getAll() {
        return Arrays.asList(
                new ResourcePermission() {
                    @Override
                    public String getId() {
                        return "1";
                    }

                    @Override
                    public String getTitle() {
                        return "读";
                    }

                    @Override
                    public String getPath() {
                        return "/article/**";
                    }

                    @Override
                    public String getAuth() {
                        return "USER";
                    }

                    @Override
                    public String getScope() {
                        return null;
                    }
                },
                new ResourcePermission() {
                    @Override
                    public String getId() {
                        return "2";
                    }

                    @Override
                    public String getTitle() {
                        return "管理";
                    }

                    @Override
                    public String getPath() {
                        return "/admin/**";
                    }

                    @Override
                    public String getAuth() {
                        return "admin";
                    }

                    @Override
                    public String getScope() {
                        return null;
                    }
                }
        );
    }

    @Override
    public List<ResourcePermission> getByRoleId(String roleId) {
        return getAll();
    }
}
