package com.lind.logback;

import com.lind.logback.mdc.LogInterceptor;
import com.lind.logback.trace.DefaultTraceGenerator;
import com.lind.logback.user.AnonymousCurrentUser;
import com.lind.logback.user.CurrentUser;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lind
 * @date 2023/1/28 11:14
 * @since 1.0.0
 */
@Configuration
public class LoggerConfig {

	@Bean
	@ConditionalOnMissingBean(CurrentUser.class)
	public CurrentUser currentUser() {
		return new AnonymousCurrentUser();
	}

	@Bean
	@ConditionalOnMissingBean(DefaultTraceGenerator.class)
	public DefaultTraceGenerator traceGenerator() {
		return new DefaultTraceGenerator();
	}

	@Bean
	public LogInterceptor logInterceptor(CurrentUser currentUser) {
		return new LogInterceptor(currentUser);
	}

}
