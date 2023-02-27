package com.lind.micro.product;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication()
@EnableDiscoveryClient
@RestController
public class ProductApplication {

	@SneakyThrows
	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/product")
	public String getStores(@RequestHeader("Authorization") String currentUserId) {
		return "ok" + currentUserId;
	}

}
