package com.lind.rbac.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.ImmutableMap;
import com.lind.common.dto.PageDTO;
import com.lind.common.rest.CommonResult;
import com.lind.common.util.BinFlagUtils;
import com.lind.rbac.dao.PermissionDao;
import com.lind.rbac.dao.RoleDao;
import com.lind.rbac.dao.UserDao;
import com.lind.rbac.dao.UserRoleDao;
import com.lind.rbac.entity.Permission;
import com.lind.rbac.entity.Role;
import com.lind.rbac.entity.User;
import com.lind.rbac.entity.UserRole;
import com.lind.uaa.jwt.config.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "用户接口")
@RestController
@RequestMapping("user")
public class UserController {
  @Autowired
  UserDao userDao;
  @Autowired
  PermissionDao permissionDao;
  @Autowired
  SecurityUtil securityUtil;
  @Autowired
  UserRoleDao userRoleDao;
  @Autowired
  RoleDao roleDao;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @ApiOperation("列表")
  @GetMapping
  public CommonResult<IPage<User>> index(
      @ApiParam("页码") PageDTO pageDTO) {

    QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
    return CommonResult.ok(userDao.selectPage(
        new Page<>(pageDTO.getPageNumber(), pageDTO.getPageSize()),
        userQueryWrapper));
  }

  @ApiOperation("获取页面上的按钮,path和title二选一即可")
  @GetMapping("page-btn")
  @Cacheable(cacheNames = "page-btn")
  public CommonResult getPermissionByRoleId(@ApiParam("路径") @RequestParam(required = false) String path,
                                            @ApiParam("模型名称") @RequestParam(required = false) String title) {
    if (securityUtil.getCurrUser() == null) {
      return CommonResult.unauthorizedFailure("需要先进行登录");
    }
    QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
    if (StringUtils.isNotBlank(path)) {
      queryWrapper.lambda().eq(Permission::getPath, path);
    }
    if (StringUtils.isNotBlank(title)) {
      queryWrapper.lambda().eq(Permission::getTitle, title);
    }
    Integer roleBtnSum = 0;
    List<UserRole> userRoles = userRoleDao.selectList(new QueryWrapper<UserRole>().lambda()
        .eq(UserRole::getUserId, securityUtil.getCurrUser().getId()));
    if (!CollectionUtils.isEmpty(userRoles)) {
      List<Role> roles = roleDao.selectList(new QueryWrapper<Role>().lambda()
          .in(Role::getId, userRoles.stream().map(o -> o.getRoleId()).collect(Collectors.toList())));

      if (!CollectionUtils.isEmpty(roles)) {

        for (Role o : roles) {
          roleBtnSum = roleBtnSum | o.getButtonGrant();
        }
      }
    }
    List<Permission> list = permissionDao.selectList(queryWrapper);
    Integer finalRoleBtnSum = roleBtnSum;
    return CommonResult.ok(
        list.stream().map(i -> ImmutableMap.of(
            "title", i.getTitle(),
            "path", i.getPath(),
            "bulkButtonList", i.getBulkButtonList().stream().filter(j -> BinFlagUtils.hasValue(finalRoleBtnSum, j)).collect(Collectors.toList()),
            "rowButtonList", i.getRowButtonList().stream().filter(j -> BinFlagUtils.hasValue(finalRoleBtnSum, j)).collect(Collectors.toList())
        )).collect(Collectors.toList()));
  }

  @ApiOperation("新增")
  @PostMapping
  public CommonResult add(@RequestBody User user) {
    if (userDao.selectOne(new QueryWrapper<User>().lambda().eq(User::getUsername, user.getUsername())) != null) {
      return CommonResult.clientFailure(String.format("%s已经存在", user.getUsername()));
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userDao.insert(user);
    return CommonResult.ok();
  }

  @ApiOperation("更新")
  @PutMapping("/{id}")
  public CommonResult update(@ApiParam("用户ID") @PathVariable String id, @RequestBody User user) {
    User user1 = userDao.selectById(id);
    if (user1 != null) {
      user1.setEmail(user.getEmail());
      user1.setUsername(user.getUsername());
      userDao.updateById(user1);
    }
    return CommonResult.ok();
  }


  @ApiOperation("删除")
  @DeleteMapping("/{id}")
  public CommonResult del(@ApiParam("用户ID") @PathVariable String id) {
    QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
    userQueryWrapper.eq("id", id);
    userDao.delete(userQueryWrapper);
    return CommonResult.ok();
  }
}
