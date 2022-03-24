package com.lind.rbac.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lind.common.util.CopyUtils;
import com.lind.rbac.dao.PermissionDao;
import com.lind.rbac.entity.Permission;
import com.lind.rbac.entity.Role;
import com.lind.uaa.jwt.service.ResourcePermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("菜单管理")
@RequestMapping("menu")
public class PermissionController {

  @Autowired
  ResourcePermissionService resourcePermissionService;
  @Autowired
  PermissionDao permissionDao;

  @GetMapping
  public ResponseEntity index() {
    return ResponseEntity.ok(resourcePermissionService.getTreeMenus());
  }

  @ApiOperation("新增")
  @PostMapping
  public ResponseEntity add(@RequestBody Permission permission) {
    permissionDao.insert(permission);
    return ResponseEntity.ok().build();
  }

  @ApiOperation("更新")
  @PutMapping("/{id}")
  public ResponseEntity update(@ApiParam("菜单ID") @PathVariable String id, @RequestBody Permission permission) {
    Permission permission1 = permissionDao.selectById(id);
    if (permission1 != null) {
      CopyUtils.copyProperties(permission, permission1);
      permission1.setId(id);
      permissionDao.updateById(permission1);
    }
    return ResponseEntity.ok().build();
  }


  @ApiOperation("删除")
  @DeleteMapping("/{id}")
  public ResponseEntity del(@ApiParam("菜单ID") @PathVariable String id) {
    QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
    permissionDao.delete(new QueryWrapper<Permission>().lambda().eq(Permission::getId, id));
    return ResponseEntity.ok().build();
  }
}
