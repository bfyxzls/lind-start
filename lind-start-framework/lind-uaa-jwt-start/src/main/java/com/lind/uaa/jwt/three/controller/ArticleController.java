package com.lind.uaa.jwt.three.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("article")
public class ArticleController {
	
	@GetMapping("/index")
	public String load() {
		return "This is my first blog";
	}

	@GetMapping("/del")
	public String del() {
		return "del  my first blog";
	}

	@GetMapping("/create")
	public String create() {
		return "create  my first blog";
	}
}
