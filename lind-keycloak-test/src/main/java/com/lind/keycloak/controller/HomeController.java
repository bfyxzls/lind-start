package com.lind.keycloak.controller;

import com.lind.uaa.keycloak.config.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HomeController {

	@Autowired
	KeycloakSpringBootProperties keycloakSpringBootProperties;

	@GetMapping("/get-user1")
	public ResponseEntity getUser() {
		return ResponseEntity.ok(SecurityUser.getCurrentUserName());

	}

	@PostMapping("/uma")
	public ResponseEntity uma() {
		return ResponseEntity.ok("请求成功");
	}

	@PreAuthorize(("@pms.hasPermission('sys_user_del')"))
	@GetMapping("/userinfo")
	public ResponseEntity userinfo() {
		return ResponseEntity.ok("需要授权" + SecurityUser.getCurrentUserName());
	}

	@GetMapping("/admin/add")
	public ResponseEntity adminAdd() {
		return ResponseEntity.ok("需要admin授权" + SecurityUser.getCurrentUserName());
	}

	@GetMapping("/client_auth")
	public ResponseEntity clientAuth() {
		return ResponseEntity.ok("需要客户端授权" + SecurityUser.getCurrentUserName());
	}

}
