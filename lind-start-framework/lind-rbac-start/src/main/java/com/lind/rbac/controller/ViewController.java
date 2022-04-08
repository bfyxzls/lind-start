package com.lind.rbac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("view")
public class ViewController {
    @GetMapping("user")
    public String selectUser(Model model) {
        return "user/list";
    }
}
