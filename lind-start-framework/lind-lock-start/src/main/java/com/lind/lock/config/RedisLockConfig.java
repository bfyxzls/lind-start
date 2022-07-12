package com.lind.lock.config;

import com.lind.lock.aspect.RepeatSubmitAspect;
import com.lind.lock.template.RedisLockTemplate;
import com.lind.lock.template.RedisUserManualLockTemplate;
import com.lind.lock.template.UserIdAuditorAware;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.integration.support.locks.LockRegistry;

/**
 * redis lock bean register.
 * 同时注册RedisLockProperty这个bean，限制条件是lind.redis.lock.enable为true时注册它.
 */
@EnableConfigurationProperties(RedisLockProperty.class)
@RequiredArgsConstructor
@ConditionalOnProperty(value = "lind.redis.lock.enable", havingValue = "true", matchIfMissing = true)
public class RedisLockConfig {
    private final RedisLockProperty redisLockProperty;

    @Bean
    @ConditionalOnMissingBean(LockRegistry.class)
    public RedisLockRegistry redisLockRegistry(RedisConnectionFactory redisConnectionFactory) {
        return new RedisLockRegistry(redisConnectionFactory, redisLockProperty.getRegistryKey());
    }

    @Bean
    public RepeatSubmitAspect repeatSubmitAspect(RedisTemplate redisTemplate) {
        return new RepeatSubmitAspect(redisTemplate);
    }

    @Bean
    public RedisLockTemplate redisLockTemplate(
            RedisLockRegistry redisLockRegistry,
            RedisLockProperty redisLockProperty) {
        return new RedisLockTemplate(redisLockRegistry, redisLockProperty);
    }

    @Bean
    @ConditionalOnBean(RedisLockTemplate.class)
    public RedisUserManualLockTemplate redisUserManualLockTemplate(
            RedisTemplate<String, String> redisTemplate,
            RedisLockProperty redisLockProperty,
            RedisLockTemplate redisLockTemplate) {
        return new RedisUserManualLockTemplate(redisTemplate, redisLockProperty, redisLockTemplate);
    }
}
