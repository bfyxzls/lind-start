package com.lind.rbac.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("token")
public class AdminController {

    @GetMapping("/index")
    public String load() {
        return "This is my admin";
    }

}
