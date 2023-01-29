package com.lind.lindmanager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lind.common.locale.LocaleMessageUtils;
import com.lind.lindmanager.feign.TestClient;
import com.lind.logback.trace.OkHttpTraceIdInterceptor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@RestController
@Slf4j
@EnableFeignClients
public class LindManagerApplication {

	@Autowired
	TestClient testClient;

	public static void main(String[] args) {
		SpringApplication.run(LindManagerApplication.class, args);
	}

	@SneakyThrows
	@GetMapping(path = "m", produces = "text/html")
	public String m() {
		log.info("hello world!");
		log.warn("warning end.");
		log.error("error end.");

		OkHttpClient client = new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS)
				.readTimeout(5, TimeUnit.SECONDS).writeTimeout(5, TimeUnit.SECONDS).retryOnConnectionFailure(true)
				.addNetworkInterceptor(new OkHttpTraceIdInterceptor()).build();
		Request request = new Request.Builder().get().url("http://localhost:81/j").build();
		Call call = client.newCall(request);
		Response response = call.execute();
		log.info(response.body().string());

		testClient.j();
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("<html><body>");
		stringBuffer.append("<h1>");
		stringBuffer.append(LocaleMessageUtils.getMessage("title"));
		stringBuffer.append("</h1>");
		stringBuffer.append("<a href='/j'>");
		stringBuffer.append("超链接页面不会带上trace-id");
		stringBuffer.append("</a>");
		stringBuffer.append("</body></html>");
		return stringBuffer.toString();
	}

	@GetMapping("j")
	public ResponseEntity j() throws JsonProcessingException {
		Map<String, Object> map = new HashMap<>();
		map.put("date", LocalDate.now());
		// String result = objectMapper.writeValueAsString(map);
		log.info("j page日志!");
		return ResponseEntity.ok(map);
	}

}
