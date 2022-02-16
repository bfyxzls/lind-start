package com.lind.micro.product;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication()
@EnableDiscoveryClient
@RestController
public class ProductApplication {

  @SneakyThrows
  public static void main(String[] args) {
    SpringApplication.run(ProductApplication.class, args);
  }

  @GetMapping("product")
  public ResponseEntity product() throws InterruptedException {
    Thread.sleep(5000);
    System.out.println("hello product");
    return ResponseEntity.ok("hello product");
  }
}
