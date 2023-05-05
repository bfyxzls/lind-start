package com.lind.micro.user.feign;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "a-service", url = "http://localhost:4041",
		fallbackFactory = BaiDuClient.HystrixClientFallbackFactory.class)
public interface BaiDuClient {

	@RequestMapping(method = RequestMethod.GET, value = "test")
	ResponseEntity getHome();

	@Component
	class HystrixClientFallbackFactory implements FallbackFactory<BaiDuClient> {

		@Override
		public BaiDuClient create(Throwable cause) {
			System.err.println(cause.getMessage());
			return () -> ResponseEntity.ok("fall error baidu");
		}

	}

}
