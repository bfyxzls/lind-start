package com.lind.micro.user.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "micro-order", fallbackFactory = OrderClient.HystrixClientFallbackFactory.class)
public interface OrderClient {
  @RequestMapping(method = RequestMethod.GET, value = "/order")
  String getStores();

  @Component
  class HystrixClientFallbackFactory implements FallbackFactory<OrderClient> {

    @Override
    public OrderClient create(Throwable cause) {
      System.err.println("fall error order...");
      return () -> "fall error order...";
    }
  }
}
