package com.lind.activiti.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("index")
    public ResponseEntity index() {
        return ResponseEntity.ok("ok");
    }
}
