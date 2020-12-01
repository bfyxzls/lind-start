package com.lind.start.test.controller;

import com.lind.start.test.dto.UserDTO;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.TimeZone;

@RestController
public class KeyCloakTestController {
    final static Logger logger = LoggerFactory.getLogger(KeyCloakTestController.class);

    @GetMapping(path = "/test")
    public ResponseEntity test() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("lind");
        userDTO.setEmail("ok");
        userDTO.setTotal(100d);
        userDTO.setTotalMoney(BigDecimal.valueOf(5000));
        TimeZone time = TimeZone.getTimeZone("Etc/GMT-8");  //转换为中国时区

        TimeZone.setDefault(time);
        userDTO.setBirthday(new Date());
        userDTO.setGetup(new Date());
        return ResponseEntity.ok(userDTO);
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
