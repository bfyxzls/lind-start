package com.lind.lindmanager.controller;

import com.lind.lindmanager.Color;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    // url:/color?color=red
    @GetMapping("color")
    public ResponseEntity color(Color color) {
        return ResponseEntity.ok(color);
    }

    // url:/color/1
    @GetMapping("color1")
    public ResponseEntity color() {
        return ResponseEntity.ok().build();
    }
}
