package com.lind.micro.user.controller;

import com.lind.feign.NextHttpHeader;
import com.lind.micro.user.feign.OrderClient;
import com.lind.micro.user.feign.StoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope // 配置自动更新
@RestController
@EnableCircuitBreaker
public class HelloController {

	@Value("${const.email:lind}")
	String author;

	@Autowired
	StoreClient storeClient;

	@Autowired
	OrderClient orderClient;

	@GetMapping("hello2")
	public ResponseEntity hello2() throws InterruptedException {


		 NextHttpHeader.set("User-Id", "101"); NextHttpHeader.set("Authorization",
		 "bearer xxaabb1901"); NextHttpHeader.set("Real-Ip", "ie");


		return ResponseEntity.ok("hello," + storeClient.getStores());
	}

}
