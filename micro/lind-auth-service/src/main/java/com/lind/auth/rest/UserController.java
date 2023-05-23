package com.lind.auth.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author lind
 * @date 2023/5/23 8:12
 * @since 1.0.0
 */
@RestController
@RequestMapping("user")
public class UserController {

	/**
	 * 获取授权的用户信息
	 * @param principal 当前用户
	 * @return 授权信息
	 */
	@GetMapping("current")
	public Principal user(Principal principal) {
		return principal;
	}

	@GetMapping("read")
	@PreAuthorize("hasAuthority('read')")
	public ResponseEntity read() {
		return ResponseEntity.ok("需要有read权限才能访问");
	}

	@GetMapping("admin")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity admin() {
		return ResponseEntity.ok("需要有ADMIN权限才能访问，权限对大小写敏感");
	}
}
