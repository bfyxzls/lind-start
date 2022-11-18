package com.lind.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lind.admin.annotation.PermissionLimit;
import com.lind.admin.dao.PermissionDao;
import com.lind.admin.entity.Permission;
import com.lind.admin.util.I18nUtil;
import com.lind.admin.util.ReturnT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xuxueli 2019-05-04 16:39:50
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {


    @Autowired
    private PermissionDao permissionDao;

    @RequestMapping
    @PermissionLimit(adminuser = true)
    public String index(Model model) {
        return "permission/index";
    }

    @RequestMapping("/pageList")
    @ResponseBody
    @PermissionLimit(adminuser = true)
    public IPage<Permission> pageList(Page page) {

        // page list
        return permissionDao.selectPage(page, new QueryWrapper<>());
    }

    @RequestMapping("/add")
    @ResponseBody
    @PermissionLimit(adminuser = true)
    public ReturnT<String> add(Permission permission) {


        // check repeat
        Permission existPermission = permissionDao.selectOne(new QueryWrapper<Permission>().lambda().eq(Permission::getName, permission.getName()));
        if (existPermission != null) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, I18nUtil.getString("permission_name_repeat"));
        }

        // write
        permissionDao.insert(permission);
        return ReturnT.SUCCESS;
    }

    @RequestMapping("/update")
    @ResponseBody
    @PermissionLimit(adminuser = true)
    public ReturnT<String> update(HttpServletRequest request, Permission permission) {
        // write
        permissionDao.updateById(permission);
        return ReturnT.SUCCESS;
    }

    @RequestMapping("/remove")
    @ResponseBody
    @PermissionLimit(adminuser = true)
    public ReturnT<String> remove(HttpServletRequest request, int id) {
        permissionDao.deleteById(id);
        return ReturnT.SUCCESS;
    }

}
