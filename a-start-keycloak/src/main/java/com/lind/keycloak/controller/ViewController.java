package com.lind.keycloak.controller;

import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class ViewController {

    @GetMapping("/index")
    public String index() {
        return "view/index";
    }


    @SneakyThrows
    @GetMapping("/redirect")
    public void index(HttpServletResponse response) {
        response.sendRedirect("http://192.168.3.181:9090/ok");
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
