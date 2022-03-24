package com.lind.rbac;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lind.rbac.dao")
public class RbacApplication {
  public static void main(String[] args) {
    SpringApplication.run(RbacApplication.class, args);
  }

}
