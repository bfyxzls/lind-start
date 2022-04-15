package com.lind.rbac.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.lind.common.dto.PageDTO;
import com.lind.common.rest.CommonResult;
import com.lind.common.util.CopyUtils;
import com.lind.rbac.dao.PermissionDao;
import com.lind.rbac.dao.RolePermissionDao;
import com.lind.rbac.dto.PermissionDTO;
import com.lind.rbac.entity.Permission;
import com.lind.rbac.entity.Role;
import com.lind.rbac.entity.RolePermission;
import com.lind.redis.service.RedisService;
import com.lind.uaa.jwt.config.Constants;
import com.lind.uaa.jwt.entity.ResourcePermission;
import com.lind.uaa.jwt.service.ResourcePermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "菜单管理")
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    ResourcePermissionService resourcePermissionService;
    @Autowired
    PermissionDao permissionDao;
    @Autowired
    RedisService redisService;
    @Autowired
    RolePermissionDao rolePermissionDao;

    @ApiOperation("所有树形菜单")
    @GetMapping
    public CommonResult index() {
        return CommonResult.ok(resourcePermissionService.getTreeMenus());
    }

    @ApiOperation("登录用户的树形菜单")
    @GetMapping("user-tree")
    public CommonResult currentUserPermissionIndex() {
        return CommonResult.ok(resourcePermissionService.getRoleTreeMenus());
    }

    @ApiOperation("菜单面包绡")
    @GetMapping("father/{id}")
    public CommonResult breadcrumb(@PathVariable String id) {
        List<ResourcePermission> list = new ArrayList<>();
        Permission permission = permissionDao.selectById(id);
        resourcePermissionService.findFather(permission, list);
        return CommonResult.ok(Lists.reverse(list));
    }


    /**
     * 列表页
     *
     * @param pageDTO json raw参数体.
     * @return
     */
    @ApiOperation("列表页")
    @GetMapping("query")
    public CommonResult list(@ApiParam("分页") PageDTO pageDTO) {
        QueryWrapper<Permission> userQueryWrapper = new QueryWrapper<>();
        IPage<Permission> list = permissionDao.selectPage(
                new Page<>(pageDTO.getPageNumber(), pageDTO.getPageSize()),
                userQueryWrapper);

        return CommonResult.ok(list);
    }

    @ApiOperation("新增")
    @PostMapping
    public CommonResult add(@RequestBody PermissionDTO permission) {
        if (permissionDao.selectOne(new QueryWrapper<Permission>().lambda()
                .eq(Permission::getTitle, permission.getTitle())
                .eq(Permission::getParentId, permission.getParentId())) != null) {
            return CommonResult.clientFailure(String.format("%s在同一父级下已经存在", permission.getTitle()));
        }
        Permission permissionEntity = new Permission();
        CopyUtils.copyProperties(permission, permissionEntity);
        permissionDao.insert(permissionEntity);
        //为管理员添加菜单权限
        RolePermission rolePermission = new RolePermission();
        rolePermission.setPermissionId(permissionEntity.getId());
        rolePermission.setRoleId("1");
        rolePermissionDao.insert(rolePermission);
        redisService.del(Constants.PERMISSION_ALL);
        return CommonResult.ok();
    }

    @ApiOperation("更新")
    @PutMapping("/{id}")
    public CommonResult update(@ApiParam("菜单ID") @PathVariable String id, @RequestBody PermissionDTO permission) {
        Permission permissionEntity = permissionDao.selectById(id);
        if (permissionEntity != null) {
            CopyUtils.copyProperties(permission, permissionEntity);
            permissionDao.updateById(permissionEntity);
            redisService.del(Constants.PERMISSION_ALL);
        }
        return CommonResult.ok();
    }


    @ApiOperation("删除")
    @DeleteMapping("/{id}")
    public CommonResult del(@ApiParam("菜单ID") @PathVariable String id) {
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        permissionDao.delete(new QueryWrapper<Permission>().lambda().eq(Permission::getId, id));
        redisService.del(Constants.PERMISSION_ALL);
        return CommonResult.ok();
    }

    @ApiOperation("获取")
    @GetMapping("/{id}")
    public CommonResult get(@ApiParam("id") @PathVariable String id) {
        return CommonResult.ok(permissionDao.selectById(id));
    }
}
