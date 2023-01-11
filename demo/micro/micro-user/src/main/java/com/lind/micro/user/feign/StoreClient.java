package com.lind.micro.user.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "micro-product", fallbackFactory = StoreClient.HystrixClientFallbackFactory.class)
public interface StoreClient {

	@RequestMapping(method = RequestMethod.GET, value = "/product")
	String getStores();

	@Component
	class HystrixClientFallbackFactory implements FallbackFactory<StoreClient> {

		@Override
		public StoreClient create(Throwable cause) {
			System.err.println("fall error product");
			return () -> "fall error product";
		}

	}

}
