package com.lind.rbac.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lind.rbac.dao.UserDao;
import com.lind.rbac.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("用户接口")
@RestController
@RequestMapping("user")
public class UserController {
  @Autowired
  UserDao userDao;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @ApiOperation("列表")
  @GetMapping
  public ResponseEntity<IPage<User>> index(
      @ApiParam("页码") @RequestParam(required = false) Integer pageNumber,
      @ApiParam("显示条数") @RequestParam(required = false) Integer pageSize) {
    pageNumber = (pageNumber == null) ? 1 : pageNumber;
    pageSize = (pageSize == null) ? 10 : pageSize;

    QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
    return ResponseEntity.ok(userDao.selectPage(
        new Page<>(pageNumber, pageSize),
        userQueryWrapper));
  }

  @ApiOperation("新增")
  @PostMapping
  public ResponseEntity add(@RequestBody User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userDao.insert(user);
    return ResponseEntity.ok().build();
  }

  @ApiOperation("更新")
  @PutMapping("/{id}")
  public ResponseEntity update(@ApiParam("用户ID") @PathVariable String id, @RequestBody User user) {
    User user1 = userDao.selectById(id);
    if (user1 != null) {
      user1.setEmail(user.getEmail());
      user1.setUsername(user.getUsername());
      userDao.updateById(user1);
    }
    return ResponseEntity.ok().build();
  }


  @ApiOperation("删除")
  @DeleteMapping("/{id}")
  public ResponseEntity del(@ApiParam("用户ID") @PathVariable String id) {
    QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
    userQueryWrapper.eq("id", id);
    userDao.delete(userQueryWrapper);
    return ResponseEntity.ok().build();
  }
}
