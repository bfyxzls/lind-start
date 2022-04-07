package com.lind.rbac.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lind.common.dto.PageDTO;
import com.lind.common.rest.CommonResult;
import com.lind.common.util.CopyUtils;
import com.lind.rbac.dao.PermissionDao;
import com.lind.rbac.entity.Permission;
import com.lind.rbac.entity.Role;
import com.lind.uaa.jwt.service.ResourcePermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("permission")
public class PermissionController {

  @Autowired
  ResourcePermissionService resourcePermissionService;
  @Autowired
  PermissionDao permissionDao;

  @ApiOperation("树形展示")
  @GetMapping
  public CommonResult index() {
    return CommonResult.ok(resourcePermissionService.getTreeMenus());
  }

  /**
   * 列表页
   * @param pageDTO json raw参数体.
   * @return
   */
  @ApiOperation("列表页")
  @PostMapping("query")
  public CommonResult list(@ApiParam("分页") @RequestBody PageDTO pageDTO) {
    QueryWrapper<Permission> userQueryWrapper = new QueryWrapper<>();
    IPage<Permission> list = permissionDao.selectPage(
        new Page<>(pageDTO.getPageNumber(), pageDTO.getPageSize()),
        userQueryWrapper);

    return CommonResult.ok(list);
  }

  @ApiOperation("新增")
  @PostMapping
  public CommonResult add(@RequestBody Permission permission) {
    permissionDao.insert(permission);
    return CommonResult.ok();
  }

  @ApiOperation("更新")
  @PutMapping("/{id}")
  public CommonResult update(@ApiParam("菜单ID") @PathVariable String id, @RequestBody Permission permission) {
    Permission permission1 = permissionDao.selectById(id);
    if (permission1 != null) {
      CopyUtils.copyProperties(permission, permission1);
      permission1.setId(id);
      permissionDao.updateById(permission1);
    }
    return CommonResult.ok();
  }


  @ApiOperation("删除")
  @DeleteMapping("/{id}")
  public CommonResult del(@ApiParam("菜单ID") @PathVariable String id) {
    QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
    permissionDao.delete(new QueryWrapper<Permission>().lambda().eq(Permission::getId, id));
    return CommonResult.ok();
  }
}
