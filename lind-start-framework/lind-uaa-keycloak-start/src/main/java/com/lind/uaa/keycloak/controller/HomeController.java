package com.lind.uaa.keycloak.controller;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Map;

@RestController
public class HomeController {
    final static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping(path = "/callback")
    public void callback(@RequestParam String code, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(code);
    }
    @GetMapping("/index")
    public String index() {
        return "success index!";
    }

    @GetMapping("/email")
    public String email() {
        return "success email!";
    }

    @GetMapping("/products")
    public String products() {
        return "success products!";
    }

    @GetMapping("/write")
    public String write() {
        return "success write!";
    }


    @GetMapping(path = "/users")
    public String getUserInfo() {
        logger.info("/users get.");
        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext()
                .getAuthentication();
        final Principal principal = (Principal) authentication.getPrincipal();
        return principal.getName();
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public String logout(HttpServletRequest request) {
        try {
            request.logout();
            return "logout success";
        } catch (ServletException e) {
            return "logout fail";
        }
    }
}
