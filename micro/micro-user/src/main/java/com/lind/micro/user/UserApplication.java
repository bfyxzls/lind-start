package com.lind.micro.user;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication()
@EnableDiscoveryClient

public class UserApplication {

  @SneakyThrows
  public static void main(String[] args) {
    SpringApplication.run(UserApplication.class, args);
  }

}
