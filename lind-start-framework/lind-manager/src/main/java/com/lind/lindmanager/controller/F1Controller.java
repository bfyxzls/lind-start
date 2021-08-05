package com.lind.lindmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("free")
public class F1Controller {
    @RequestMapping("list")
    public String selectUser(){
        return "f1/list";
    }
}
