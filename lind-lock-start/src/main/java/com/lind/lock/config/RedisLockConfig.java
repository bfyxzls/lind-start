package com.lind.lock.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.integration.support.locks.LockRegistry;

/**
 * redis lock bean register.
 */
@Configuration
public class RedisLockConfig {
    @Autowired
    RedisLockProperty redisLockProperty;

    @Bean
    @ConditionalOnMissingBean(LockRegistry.class)
    public RedisLockRegistry redisLockRegistry(RedisConnectionFactory redisConnectionFactory) {
        return new RedisLockRegistry(redisConnectionFactory, redisLockProperty.getRegistryKey());
    }
}