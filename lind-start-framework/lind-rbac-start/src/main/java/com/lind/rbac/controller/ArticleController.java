package com.lind.rbac.controller;

import com.lind.uaa.jwt.config.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    SecurityUtil securityUtil;

    @GetMapping("/index")
    public String load() {
        return "This is my first blog," +
                securityUtil.getCurrUser().getId() +
                securityUtil.getCurrUser().getEmail();
    }

    @GetMapping("/del")
    public String del() {
        return "del  my first blog";
    }

    @GetMapping("/create")
    public String create() {
        return "create  my first blog";
    }
}
