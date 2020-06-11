package com.lind.start.test.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/ok")
    public ResponseEntity ok() {
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/e")
    public ResponseEntity e() {
        int a=0;
        int c=1/a;
        return ResponseEntity.ok("ok");
    }
}