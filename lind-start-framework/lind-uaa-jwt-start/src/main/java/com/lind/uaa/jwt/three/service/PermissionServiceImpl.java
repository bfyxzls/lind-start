package com.lind.uaa.jwt.three.service;

import com.lind.uaa.jwt.entity.ResourcePermission;
import com.lind.uaa.jwt.service.ResourcePermissionService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PermissionServiceImpl implements ResourcePermissionService {
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
                        return "文章列表";
                    }

                    @Override
                    public String getPath() {
                        return "/article/**";
                    }
                },
                new ResourcePermission() {
                    @Override
                    public String getId() {
                        return "1";
                    }

                    @Override
                    public String getTitle() {
                        return "文章审核";
                    }

                    @Override
                    public String getPath() {
                        return "/article/audit**";
                    }
                },
                new ResourcePermission() {
                    @Override
                    public String getId() {
                        return "1";
                    }

                    @Override
                    public String getTitle() {
                        return "文章添加";
                    }

                    @Override
                    public String getPath() {
                        return "/article/create**";
                    }
                },
                new ResourcePermission() {
                    @Override
                    public String getId() {
                        return "2";
                    }

                    @Override
                    public String getTitle() {
                        return "系统管理";
                    }

                    @Override
                    public String getPath() {
                        return "/admin/**";
                    }
                }
        );
    }

    @Override
    public List<ResourcePermission> getByRoleId(String roleId) {
        return getAll();
    }

    @Override
    public List<ResourcePermission> getByUserId(String userId) {
        return Arrays.asList(
                new ResourcePermission() {
                    @Override
                    public String getId() {
                        return "1";
                    }

                    @Override
                    public String getTitle() {
                        return "文章列表";
                    }

                    @Override
                    public String getPath() {
                        return "/article/**";
                    }

                },   new ResourcePermission() {
                    @Override
                    public String getId() {
                        return "1";
                    }

                    @Override
                    public String getTitle() {
                        return "文章添加";
                    }

                    @Override
                    public String getPath() {
                        return "/article/create**";
                    }
                });
    }
}
