package com.lind.lindmanager;

import com.lind.common.locale.LocaleMessageUtils;
import com.lind.lindmanager.feign.TestClient;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.SocketException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootApplication
@RestController
@Slf4j
@EnableFeignClients
public class LindManagerApplication {

	@Autowired
	TestClient testClient;

	@Autowired
	ThreadPoolTaskExecutor threadPoolExecutorMdcWrapper;

	public static void main(String[] args) {
		SpringApplication.run(LindManagerApplication.class, args);
	}

	@SneakyThrows
	@GetMapping(path = "m", produces = "text/html")
	public String m() {
		log.info("hello world!");
		log.warn("warning end.");
		log.error("error end.");
		threadPoolExecutorMdcWrapper.execute(() -> {
			log.info("thread is life");
		});
		// OkHttpClient client = new OkHttpClient.Builder().connectTimeout(5,
		// TimeUnit.SECONDS)
		// .readTimeout(5, TimeUnit.SECONDS).writeTimeout(5,
		// TimeUnit.SECONDS).retryOnConnectionFailure(true)
		// .addNetworkInterceptor(new OkHttpTraceIdInterceptor()).build();
		// Request request = new
		// Request.Builder().get().url("http://localhost:81/j").build();
		// Call call = client.newCall(request);
		// Response response = call.execute();
		// log.info(response.body().string());

		testClient.j();

		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(4);
		executor.setQueueCapacity(50);
		executor.setKeepAliveSeconds(60);
		executor.setThreadNamePrefix("TEST-A-");
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
		executor.initialize();

		//不同的线程，MDC不共享
		executor.execute(() -> {
			MDC.put("test", "bo");
			System.out.println(String.format("t:%s,MDC:%s", Thread.currentThread().getName(), MDC.get("test")));
		});
		executor.execute(() -> {
			System.out.println(String.format("t:%s,MDC:%s", Thread.currentThread().getName(), MDC.get("test")));
		});
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
	public ResponseEntity j() throws IOException, InterruptedException {
		Map<String, Object> map = new HashMap<>();
		map.put("date", LocalDate.now());
		// String result = objectMapper.writeValueAsString(map);
		log.info("j page日志!");
		// Thread.sleep(1000 * 20);
		throw new SocketException("默认只有IOException异常才会发起重拾");

		// return ResponseEntity.ok(map);
	}

}
