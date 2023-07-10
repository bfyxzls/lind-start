package com.lind.start.test.controller;

import com.lind.redis.lock.annotation.RepeatSubmit;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lind
 * @date 2022/7/12 10:57
 * @since 1.0.0
 */
@RestController
public class DupController {

	@GetMapping("dup")
	@RepeatSubmit(expireTime = 100)
	public ResponseEntity dup() {
		return ResponseEntity.ok("success");
	}

}
