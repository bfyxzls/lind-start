package com.lind.lindmanager.controller;

import com.lind.lindmanager.util.FreeMarkerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("free")
public class F1Controller {
    @Autowired
    FreeMarkerUtil freeMarkerUtil;

    @RequestMapping("list")
    public String selectUser() {
        return "f1/list";
    }

}
