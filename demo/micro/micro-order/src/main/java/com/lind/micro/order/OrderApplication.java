package com.lind.micro.order;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication()
@EnableDiscoveryClient
@EnableFeignClients
@RestController
public class OrderApplication {

	@SneakyThrows
	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	@GetMapping("order")
	public ResponseEntity product() throws InterruptedException {
		System.out.println("hello order");
		Thread.sleep(3000);
		return ResponseEntity.ok("hello order");
	}

}
