package com.lind.logback;

import com.lind.logback.mdc.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author lind
 * @date 2023/1/28 12:01
 * @since 1.0.0
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

	@Autowired
	private LogInterceptor logInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(logInterceptor);
		super.addInterceptors(registry);
	}

}
