package com.lind.nacos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TestApplication {
  @Autowired
  TestConfig testConfig;

  public static void main(String[] args) {
    SpringApplication.run(TestApplication.class, args);
  }

  @GetMapping("hello")
  public String hello() {
    return testConfig.getName();
  }
}
