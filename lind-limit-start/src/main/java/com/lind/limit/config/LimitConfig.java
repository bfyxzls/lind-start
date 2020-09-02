package com.lind.limit;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author lind.
 * @EnableConfigurationProperties注册LimitProperties的bean 他里面的bean以来于lind.limit.enable的值
 */
@EnableConfigurationProperties(LimitProperties.class)
@ConditionalOnProperty(value = "lind.limit.enable", havingValue = "true", matchIfMissing = true)
public class LimitConfig {
    @Bean
    public LimitRaterInterceptor limitRaterInterceptor() {
        return new LimitRaterInterceptor();
    }

    @Bean
    public RedisRaterLimiter redisRaterLimiter() {
        return new RedisRaterLimiter();
    }
}
