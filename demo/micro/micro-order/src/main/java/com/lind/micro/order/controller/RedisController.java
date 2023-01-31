package com.lind.micro.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author lind
 * @date 2022/10/24 11:30
 * @since 1.0.0
 */
@RestController()
@RequestMapping("redis")
public class RedisController {

	@Autowired
	RedisTemplate<String, String> redisService;

	@GetMapping("set")
	public ResponseEntity set() {
		redisService.opsForValue().set("test", new Date().toString());
		return ResponseEntity.ok("success");
	}

	@GetMapping("se2t")
	public ResponseEntity set2() {
		redisService.opsForValue().set("test2", new Date().toString());
		return ResponseEntity.ok("success");
	}

}
