package com.lind.rbac.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.ImmutableMap;
import com.lind.common.dto.DateRangeDTO;
import com.lind.common.dto.PageDTO;
import com.lind.common.rest.CommonResult;
import com.lind.common.util.BinFlagUtils;
import com.lind.common.util.CopyUtils;
import com.lind.rbac.dao.PermissionDao;
import com.lind.rbac.dao.RoleDao;
import com.lind.rbac.dao.UserDao;
import com.lind.rbac.dao.UserRoleDao;
import com.lind.rbac.dto.UserDTO;
import com.lind.rbac.entity.Permission;
import com.lind.rbac.entity.Role;
import com.lind.rbac.entity.User;
import com.lind.rbac.entity.UserRole;
import com.lind.rbac.vo.UserVO;
import com.lind.redis.service.RedisService;
import com.lind.uaa.jwt.config.Constants;
import com.lind.uaa.jwt.config.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
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
    RedisService redisService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @ApiOperation("列表")
    @GetMapping
    public CommonResult<IPage<UserVO>> index(
            @ApiParam("时间")  DateRangeDTO rangeDTO,
            @ApiParam("页码") PageDTO pageDTO) {

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        if (rangeDTO.getFromDate() != null) {
            userQueryWrapper.lambda().ge(User::getCreateTime, rangeDTO.getFromDate());
        }
        if (rangeDTO.getToDate() != null) {
            userQueryWrapper.lambda().le(User::getCreateTime, rangeDTO.getToDate().plusDays(1));
        }
        userQueryWrapper.lambda().orderByDesc(User::getUpdateTime);
        Page<User> result = userDao.selectPage(
                new Page<>(pageDTO.getPageNumber(), pageDTO.getPageSize()),
                userQueryWrapper);
        List<UserVO> userVOS = CopyUtils.copyListProperties(result.getRecords(), UserVO.class);

        for (UserVO userVO : userVOS) {
            List<String> userRoleIds = userRoleDao.selectList(
                    new QueryWrapper<UserRole>().lambda().eq(UserRole::getUserId, userVO.getId())).stream().map(o -> o.getRoleId()).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(userRoleIds)) {
                List<Role> roles = roleDao.selectList(new QueryWrapper<Role>().lambda().in(Role::getId, userRoleIds));
                if (!CollectionUtils.isEmpty(roles))
                    userVO.setRoleList(roles);
            }
        }
        IPage<UserVO> userVOIPage = new Page();
        userVOIPage.setRecords(userVOS);
        userVOIPage.setPages(result.getPages());
        userVOIPage.setSize(result.getSize());
        userVOIPage.setTotal(result.getTotal());
        return CommonResult.ok(userVOIPage);
    }

    @GetMapping("{id}")
    public CommonResult<UserVO> index(@ApiParam("用户id") @PathVariable String id) {
        return CommonResult.ok(CopyUtils.copyProperties(userDao.selectById(id),UserVO.class));
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
    public CommonResult add(@RequestBody @Validated UserDTO user) {
        if (userDao.selectCount(
                new QueryWrapper<User>().lambda().eq(User::getUsername, user.getUsername())
                        .or().eq(User::getPhone, user.getPhone())
                        .or().eq(User::getEmail, user.getEmail())) > 0) {
            return CommonResult.clientFailure(String.format("用户已经存在【%s %s %s】", user.getUsername(), user.getPhone(), user.getEmail()));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userEntity = new User();
        CopyUtils.copyProperties(user, userEntity);
        userDao.insert(userEntity);
        if (!CollectionUtils.isEmpty(user.getRoleIdList())) {
            for (String roleId : user.getRoleIdList()) {
                userRoleDao.insert(UserRole.builder()
                        .userId(userEntity.getId())
                        .roleId(roleId)
                        .build());
            }
        }
        return CommonResult.ok();
    }

    @ApiOperation("更新")
    @PutMapping("/{id}")
    public CommonResult update(@ApiParam("用户ID") @PathVariable String id, @RequestBody UserDTO user) {
        User userEntity = userDao.selectById(id);
        if (userEntity != null) {
            CopyUtils.copyProperties(user, userEntity);
            userDao.updateById(userEntity);
            if (!CollectionUtils.isEmpty(user.getRoleIdList())) {
                userRoleDao.delete(new QueryWrapper<UserRole>().lambda().eq(UserRole::getUserId, id));
                for (String roleId : user.getRoleIdList()) {
                    userRoleDao.insert(UserRole.builder()
                            .userId(id)
                            .roleId(roleId)
                            .build());
                }
            }
            redisService.del(Constants.USER_PERMISSION.concat(id));
        }
        return CommonResult.ok();
    }


    @ApiOperation("删除")
    @DeleteMapping("/{id}")
    public CommonResult del(@ApiParam("用户ID") @PathVariable String id) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id", id);
        userDao.delete(userQueryWrapper);
        redisService.del(Constants.USER_PERMISSION.concat(id));
        return CommonResult.ok();
    }
}
