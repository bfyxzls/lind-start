package com.lind.lindmanager;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@Slf4j
public class LindManagerApplication {

	@Autowired
	ThreadPoolTaskExecutor threadPoolExecutorMdcWrapper;

	public static void main(String[] args) {
		SpringApplication.run(LindManagerApplication.class, args);
	}

	@SneakyThrows
	@GetMapping(path = "s")
	public ResponseEntity s(@RequestParam String wd) {
		log.info("hello world!{}", wd);
		return ResponseEntity.ok(wd);
	}

	@GetMapping("j")
	public ResponseEntity j() throws IOException, InterruptedException {
		Map<String, Object> map = new HashMap<>();
		map.put("date", LocalDate.now());
		// String result = objectMapper.writeValueAsString(map);
		log.info("j page日志!");

		return ResponseEntity.ok(map);
	}

}
