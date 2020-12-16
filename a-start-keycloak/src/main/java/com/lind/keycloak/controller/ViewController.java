package com.lind.keycloak.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/index")
    public String index() {
        return "view/index";
    }

    @GetMapping("/sifa")
    public String sifa() {
        return "view/sifa";
    }

    @GetMapping("/lvsuo")
    public String lvsuo() {
        return "view/lvsuo";
    }

    @GetMapping("/faxue")
    public String faxue() {
        return "view/faxue";
    }

    @GetMapping("/test")
    public String test() {
        return "view/test";
    }
}
