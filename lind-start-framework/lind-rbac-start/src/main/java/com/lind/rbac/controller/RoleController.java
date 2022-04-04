package com.lind.rbac.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lind.common.dto.PageDTO;
import com.lind.common.rest.CommonResult;
import com.lind.mybatis.util.QueryFactory;
import com.lind.rbac.dao.RoleDao;
import com.lind.rbac.dao.RolePermissionDao;
import com.lind.rbac.dto.RoleDTO;
import com.lind.rbac.entity.Role;
import com.lind.rbac.entity.RolePermission;
import com.lind.redis.service.RedisService;
import com.lind.uaa.jwt.config.Constants;
import com.lind.uaa.jwt.service.ResourcePermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "角色管理")
@RestController
@RequestMapping("role")
public class RoleController {
  @Autowired
  RoleDao roleDao;
  @Autowired
  RolePermissionDao rolePermissionDao;
  @Autowired
  ResourcePermissionService resourcePermissionService;
  @Autowired
  RedisService redisService;

  @ApiOperation("列表")
  @GetMapping
  public CommonResult<IPage<RoleDTO>> index(
      @ApiParam("页码") PageDTO pageDTO) {
    QueryWrapper<Role> userQueryWrapper = new QueryWrapper<>();
    IPage<Role> roleList = roleDao.selectPage(
        new Page<>(pageDTO.getPageNumber(), pageDTO.getPageSize()),
        userQueryWrapper);

    List<RoleDTO> roles = roleList.getRecords()
        .stream()
        .map(o -> {
          RoleDTO role = new RoleDTO();
          role.setId(o.getId());
          role.setName(o.getName());
          role.setButtonGrantList(o.getButtonGrantList());
          role.setPermissionList(resourcePermissionService.getAllByRoleId(o.getId()));
          return role;
        }).collect(Collectors.toList());

    IPage<RoleDTO> roleDTOIPage = new Page();
    roleDTOIPage.setRecords(roles);
    roleDTOIPage.setPages(roleList.getPages());
    roleDTOIPage.setSize(roleList.getSize());
    roleDTOIPage.setTotal(roleList.getTotal());
    return CommonResult.ok(roleDTOIPage);
  }

  @ApiOperation("列表检索")
  @PostMapping("query")
  public CommonResult<IPage<Role>> query(@RequestBody RoleDTO roleDTO) {

    QueryWrapper<Role> userQueryWrapper = QueryFactory.entityToWrapper(roleDTO);
    return CommonResult.ok(roleDao.selectPage(
        new Page<>(1, 10),
        userQueryWrapper));
  }

  @ApiOperation("新增")
  @PostMapping
  public CommonResult add(@RequestBody RoleDTO roleDTO) {
    if (roleDao.selectOne(new QueryWrapper<Role>().lambda().eq(Role::getName, roleDTO.getName())) != null) {
      return CommonResult.clientFailure(String.format("%s已经存在", roleDTO.getName()));
    }
    Role role = new Role();
    role.setName(roleDTO.getName());
    role.addGrant(roleDTO.getButtonGrantList());
    roleDao.insert(role);
    return CommonResult.ok();
  }

  @ApiOperation("更新")
  @PutMapping("/{id}")
  public CommonResult update(@ApiParam("角色ID") @PathVariable String id, @RequestBody RoleDTO roleDTO) {
    Role role = roleDao.selectById(id);
    if (role != null) {
      role.setName(roleDTO.getName());
      role.addGrant(roleDTO.getButtonGrantList());
      roleDao.updateById(role);
      redisService.del(Constants.ROLE_PERMISSION.concat(id));
    }
    return CommonResult.ok();
  }

  @ApiOperation("更新角色的权限")
  @PutMapping("/{id}/permission")
  @Transactional
  public CommonResult updatePermission(@ApiParam("角色ID") @PathVariable String id, @RequestBody List<String> permissions) {
    Role role = roleDao.selectById(id);
    if (role != null) {
      rolePermissionDao.delete(new QueryWrapper<RolePermission>().lambda().eq(RolePermission::getRoleId, id));
      permissions.forEach(o -> {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setPermissionId(o);
        rolePermission.setRoleId(id);
        rolePermission.setSelected(false);
        rolePermissionDao.insert(rolePermission);
      });
      redisService.del(Constants.ROLE_PERMISSION.concat(id));
    }
    return CommonResult.ok();
  }

  @ApiOperation("删除")
  @DeleteMapping("/{id}")
  public CommonResult del(@ApiParam("角色ID") @PathVariable String id) {
    QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
    roleQueryWrapper.eq("id", id);
    roleDao.delete(roleQueryWrapper);
    redisService.del(Constants.ROLE_PERMISSION.concat(id));
    return CommonResult.ok();
  }
}
