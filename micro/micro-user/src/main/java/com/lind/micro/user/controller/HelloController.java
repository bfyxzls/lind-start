package com.lind.micro.user.controller;

import com.lind.micro.user.feign.OrderClient;
import com.lind.micro.user.feign.StoreClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope // 配置自动更新
@RestController
@Slf4j
public class HelloController {

	@Value("${const.email:lind}")
	String author;

	@Autowired
	StoreClient storeClient;

	@Autowired
	OrderClient orderClient;

	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@GetMapping("hello2")
	public ResponseEntity hello2() throws InterruptedException {
		ServiceInstance instance = loadBalancerClient.choose("micro-product");
		log.info("ip:{},port:{}", instance.getHost(), instance.getPort());

		return ResponseEntity.ok("hello," + storeClient.getHome());
	}

}
