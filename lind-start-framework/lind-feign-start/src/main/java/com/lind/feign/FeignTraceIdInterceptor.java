package com.lind.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

/**
 * 基于openFeign的拦截器，处理需要向下游传递的信息.
 *
 * @author lind
 * @date 2023/1/29 11:44
 * @since 1.0.0
 */
@Configuration
public class FeignTraceIdInterceptor implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate template) {

		if (NextHttpHeader.get() != null) {
			NextHttpHeader.get().forEach(o -> {
				template.header(o, NextHttpHeader.get(o));
			});
		}
	}

}
