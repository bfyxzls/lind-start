package com.lind.lindmanager.controller;

import com.lind.common.util.FreeMarkerUtil;
import com.lind.lindmanager.util.FreeMakerUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("f1")
public class F1Controller {


	@GetMapping("/chat")
	public String sseStreamChat() {
		return "/sse/chat";
	}

	@GetMapping("/index")
	public String index() {
		return "sse/index";
	}

	@GetMapping("/list")
	public String list() {
		return "sse/list";
	}
	@GetMapping("/test-sse")
	public String testSse() {
		return "sse/test";
	}
	@RequestMapping("list")
	public String selectUser(Model model) {
		model.addAttribute("forgetPasswordAddress", "ok");
		return "f1/list";
	}

	@RequestMapping("error")
	public void error(HttpServletResponse response, @RequestParam String version) throws IOException {
		Map<String, Object> map = new HashMap<>();
		map.put("message", "程序出错");
		String html = new FreeMarkerUtil().processTemplate(map, "error/error.ftl", version);

		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(html);
	}

	@RequestMapping("error1")
	public ResponseEntity error() {
		return ResponseEntity.ok(FreeMakerUtils.outHtml("error/error.ftl", "v1", null));
	}

	@RequestMapping(value = "add")
	public String add() {
		return "f1/create";
	}

	@PostMapping(value = "add")
	public ResponseEntity add(@RequestParam Map<String, Object> map) {
		return ResponseEntity.ok(map);
	}

}
