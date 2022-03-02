package com.lind.micro.product;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;


@SpringBootApplication()
@EnableDiscoveryClient
@RestController
public class ProductApplication {

  @SneakyThrows
  public static void main(String[] args) {
    SpringApplication.run(ProductApplication.class, args);
  }

  @GetMapping("product")
  public ResponseEntity product(HttpServletRequest request) throws InterruptedException {
    Enumeration<String> headers=request.getHeaderNames();
    while (headers.hasMoreElements()) {
      String key = headers.nextElement();
      System.out.println(key + ":" + request.getHeader(key));
    }
    System.out.println("hello product");
    return ResponseEntity.ok("hello product");
  }
}
