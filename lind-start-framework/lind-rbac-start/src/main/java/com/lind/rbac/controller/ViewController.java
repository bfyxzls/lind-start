package com.lind.rbac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("view")
public class ViewController {
    @GetMapping("index")
    public String userList(Model model) {
        return "view/index";
    }

    /**
     * 登录
     */
    @GetMapping("login")
    public String login(Model model) {
        return "common/login";
    }
}
