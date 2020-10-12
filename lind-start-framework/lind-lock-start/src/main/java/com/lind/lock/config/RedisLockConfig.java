package com.lind.lock.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.integration.support.locks.LockRegistry;

/**
 * redis lock bean register.
 * 同时注册RedisLockProperty这个bean，限制条件是lind.redis.lock.enable为true时注册它.
 */
@EnableConfigurationProperties(RedisLockProperty.class)
@ConditionalOnProperty(value = "lind.redis.lock.enable", havingValue = "true", matchIfMissing = true)
public class RedisLockConfig {
    @Autowired
    RedisLockProperty redisLockProperty;

    @Bean
    @ConditionalOnMissingBean(LockRegistry.class)
    public RedisLockRegistry redisLockRegistry(RedisConnectionFactory redisConnectionFactory) {
        return new RedisLockRegistry(redisConnectionFactory, redisLockProperty.getRegistryKey());
    }
}