package com.lind.lindmanager.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lind
 * @date 2023/1/29 11:55
 * @since 1.0.0
 */
@FeignClient(name = "test", url = "http://localhost:81",
		fallbackFactory = TestClient.HystrixClientFallbackFactory.class,
configuration = SimpleRetryer.CommonFeignRetry.class)
public interface TestClient {

	@GetMapping("j")
	ResponseEntity j();

	@Component
	class HystrixClientFallbackFactory implements FallbackFactory<TestClient> {

		@Override
		public TestClient create(Throwable cause) {
			System.err.println("TestClient error");
			return () -> ResponseEntity.ok("TestClient error");
		}

	}

}
