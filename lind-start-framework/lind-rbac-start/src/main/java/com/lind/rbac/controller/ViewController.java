package com.lind.rbac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("view")
public class ViewController {
    @GetMapping("user")
    public String userList(Model model) {
        return "user/list";
    }

    /**
     * 菜单表中配置了role地址之后，这个/role就需要先认证了.
     *
     * @param model
     * @return
     */
    @GetMapping("permission")
    public String roleList(Model model) {
        return "permission/list";
    }

    /**
     * 登录
     */
    @GetMapping("login")
    public String login(Model model) {
        return "common/login";
    }
}
