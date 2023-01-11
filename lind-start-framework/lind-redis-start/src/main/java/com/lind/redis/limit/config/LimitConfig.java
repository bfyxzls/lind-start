package com.lind.redis.limit.config;

import com.lind.redis.limit.aop.LimitRaterInterceptor;
import com.lind.redis.limit.aop.RedisRaterLimiter;
import org.springframework.context.annotation.Bean;

/**
 * EnableConfigurationProperties注册LimitProperties的bean 他里面的bean以来于lind.limit.enable的值.
 */
public class LimitConfig {

	/**
	 * ConditionalOnProperty条件满足时注册自己.
	 * @return
	 */
	@Bean
	public LimitRaterInterceptor limitRaterInterceptor() {
		return new LimitRaterInterceptor();
	}

	/**
	 * ConditionalOnProperty条件满足时注册自己.
	 * @return
	 */
	@Bean
	public RedisRaterLimiter redisRaterLimiter() {
		return new RedisRaterLimiter();
	}

	@Bean
	public LimitProperties limitProperties() {
		return new LimitProperties();
	}

}
