package com.lind.lindmanager.controller;

import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller

public class CommonController {

	@SneakyThrows
	@GetMapping("login")
	public String login(Model model) {
		model.addAttribute("message", "请先进行登录");
		return "common/login";
	}

	@PostMapping("login")
	public void login(@RequestParam String username, @RequestParam String password, HttpServletResponse response)
			throws IOException {
		response.sendRedirect("/user/list");
	}

}
