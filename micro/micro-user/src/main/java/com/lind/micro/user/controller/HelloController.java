package com.lind.micro.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope//配置自动更新
@RestController
public class HelloController {
  @Value("${author:lind}")
  String author;

  @GetMapping("hello")
  public ResponseEntity hello() {
    return ResponseEntity.ok("hello," + author);
  }
}
