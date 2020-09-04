package com.lind.limit.config;

import com.lind.limit.LimitRaterInterceptor;
import com.lind.limit.RedisRaterLimiter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * EnableConfigurationProperties注册LimitProperties的bean 他里面的bean以来于lind.limit.enable的值.
 */
@EnableConfigurationProperties(LimitProperties.class)
@ConditionalOnProperty(value = "lind.limit.enable", havingValue = "true", matchIfMissing = true)
public class LimitConfig {
    /**
     * ConditionalOnProperty条件满足时注册自己.
     *
     * @return
     */
    @Bean
    public LimitRaterInterceptor limitRaterInterceptor() {
        return new LimitRaterInterceptor();
    }

    /**
     * ConditionalOnProperty条件满足时注册自己.
     *
     * @return
     */
    @Bean
    public RedisRaterLimiter redisRaterLimiter() {
        return new RedisRaterLimiter();
    }
}
