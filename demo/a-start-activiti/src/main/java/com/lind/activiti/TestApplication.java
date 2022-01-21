package com.lind.activiti;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {org.activiti.spring.boot.SecurityAutoConfiguration.class})//@EnableRunTime
public class TestApplication {
  @SneakyThrows
  public static void main(String[] args) {
    SpringApplication.run(TestApplication.class, args);
  }

}
