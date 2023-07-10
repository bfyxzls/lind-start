package com.lind.lindmanager.controller;

import com.lind.common.util.CookieUtils;
import com.lind.lindmanager.Color;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;

@RestController
public class TestController {

	// url:/color?color=red
	@GetMapping("color")
	public ResponseEntity color(Color color) {
		return ResponseEntity.ok(color);
	}

	// url:/color/1
	@GetMapping("color1")
	public ResponseEntity color() {
		return ResponseEntity.ok().build();
	}

	@GetMapping("redirect")
	public ResponseEntity url() {

		String redirect1 = "https://www.pkulaw.com/pfnl/a6bdb3332ec0adc422b1fcb995ef9bc28efc7a4640f9b30abdfb.html?keyword=(2018)民终785号&way=listView";
		// String redirect2 =
		// "https://www.pkulaw.com/pfnl/a6bdb3332ec0adc422b1fcb995ef9bc28efc7a4640f9b30abdfb.html?keyword=%282018%29%E6%9C%80%E9%AB%98%E6%B3%95%E6%B0%91%E7%BB%88785%E5%8F%B7&way=listView#anchor-documentno";
		String redirect_uri = URLEncoder.encode(redirect1); // 中文需要进行编码
		CookieUtils.addCookie("redirect_uri", redirect_uri);

		return ResponseEntity.ok().build();
	}

	@PostMapping("exit")
	public ResponseEntity exit() throws InterruptedException {
		Thread.sleep(10000);
		System.out.println("exit");
		return ResponseEntity.ok().build();
	}

	@GetMapping("single")
	public ResponseEntity testSingle() {
		String url = "http://localhost:81/exit";
		Executors.newSingleThreadExecutor().execute(() -> {
			// HttpUtil.post(url, "");
			try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
				HttpPost httpPost = new HttpPost(url);
				// 创建 ContentType 对象为 form 表单模式
				ContentType contentType = ContentType.create("application/x-www-form-urlencoded",
						StandardCharsets.UTF_8);
				// 添加到 HttpPost 头中
				httpPost.setHeader(HttpHeaders.CONTENT_TYPE, contentType.getMimeType());
				client.execute(httpPost);
			}
			catch (IOException ex) {
			}
		});

		return ResponseEntity.ok("success");
	}

}
