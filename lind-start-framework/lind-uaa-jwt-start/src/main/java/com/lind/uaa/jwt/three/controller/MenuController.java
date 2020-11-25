package com.lind.uaa.jwt.three.controller;

import com.lind.uaa.jwt.service.ResourcePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    ResourcePermissionService resourcePermissionService;

    @GetMapping("index")
    public ResponseEntity index() {
        return ResponseEntity.ok(resourcePermissionService.getTreeMenus());
    }
}
