package com.lind.rbac.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lind.rbac.dao.RoleDao;
import com.lind.rbac.dao.RolePermissionDao;
import com.lind.rbac.dto.RoleDTO;
import com.lind.rbac.entity.Role;
import com.lind.rbac.entity.RolePermission;
import com.lind.uaa.jwt.service.ResourcePermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Api("角色接口")
@RestController
@RequestMapping("role")
public class RoleController {
  @Autowired
  RoleDao roleDao;
  @Autowired
  RolePermissionDao rolePermissionDao;
 @Autowired
  ResourcePermissionService resourcePermissionService;
  @ApiOperation("列表")
  @GetMapping
  public ResponseEntity<IPage<RoleDTO>> index(
      @ApiParam("页码") @RequestParam(required = false) Integer pageNumber,
      @ApiParam("显示条数") @RequestParam(required = false) Integer pageSize) {
    pageNumber = (pageNumber == null) ? 1 : pageNumber;
    pageSize = (pageSize == null) ? 10 : pageSize;

    QueryWrapper<Role> userQueryWrapper = new QueryWrapper<>();
    IPage<Role> roleList = roleDao.selectPage(
        new Page<>(pageNumber, pageSize),
        userQueryWrapper);

    List<RoleDTO> roles = roleList.getRecords()
        .stream()
        .map(o -> {
          o.initButtonGrantList();
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
    return ResponseEntity.ok(roleDTOIPage);
  }

  @ApiOperation("新增")
  @PostMapping
  public ResponseEntity add(@RequestBody RoleDTO roleDTO) {
    Role role = new Role();
    role.setName(roleDTO.getName());
    role.addGrant(roleDTO.getButtonGrantList());
    roleDao.insert(role);
    return ResponseEntity.ok().build();
  }

  @ApiOperation("更新")
  @PutMapping("/{id}")
  public ResponseEntity update(@ApiParam("角色ID") @PathVariable String id, @RequestBody RoleDTO roleDTO) {
    Role role = roleDao.selectById(id);
    if (role != null) {
      role.setName(roleDTO.getName());
      role.addGrant(roleDTO.getButtonGrantList());
    }
    return ResponseEntity.ok().build();
  }

  @ApiOperation("更新角色的权限")
  @PutMapping("/{id}/permission")
  @Transactional
  public ResponseEntity updatePermission(@ApiParam("角色ID") @PathVariable String id, @RequestBody List<String> permissions) {
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

    }
    return ResponseEntity.ok().build();
  }

  @ApiOperation("删除")
  @DeleteMapping("/{id}")
  public ResponseEntity del(@ApiParam("角色ID") @PathVariable String id) {
    QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
    roleQueryWrapper.eq("id", id);
    roleDao.delete(roleQueryWrapper);
    return ResponseEntity.ok().build();
  }
}
