package com.lind.keycloak.controller;

import com.lind.keycloak.dto.UserDTO;
import com.lind.uaa.keycloak.permission.PermissionService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.TimeZone;

@RestController

public class KeyCloakTestController {
    final static Logger logger = LoggerFactory.getLogger(KeyCloakTestController.class);
    @Autowired
    PermissionService permissionService;

    @ApiOperation("用户信息-scope授权测试")
    @GetMapping(path = "/user/get")
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

    @ApiOperation("产品列表-role授权")
    @GetMapping("/products")
    public String products() {
        return "success products!";
    }

    @ApiOperation("退出")
    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        try {
            request.logout();
            return "logout success";
        } catch (ServletException e) {
            return "logout fail";
        }
    }

    @ApiOperation("当前用户的角色对应的权限")
    @GetMapping(value = "/permissions")
    public ResponseEntity permissions() {
        return ResponseEntity.ok(permissionService.getByRoleId("1"));
    }

}
