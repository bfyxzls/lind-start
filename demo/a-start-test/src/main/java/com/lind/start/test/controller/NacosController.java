package com.lind.start.test.controller;

import com.lind.start.test.config.UserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
@RefreshScope
public class NacosController {
  @Value("${address.city:zhangxie}")
  String city;
  @Autowired
  UserConfig userConfig;

  @GetMapping("nacos")
  public ResponseEntity nacos() {
    return ResponseEntity.ok("city=" + city + ",name=" + userConfig.getName());
  }
}
