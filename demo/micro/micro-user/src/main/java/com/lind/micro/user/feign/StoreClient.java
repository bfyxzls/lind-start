package com.lind.micro.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "micro-product")
public interface StoreClient {

	@RequestMapping(method = RequestMethod.GET, value = "/product")
	String getStores();

	@RequestMapping(method = RequestMethod.GET, value = "test")
	ResponseEntity getHome();
}
