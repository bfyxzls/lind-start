package com.lind.lock.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "lind.redis.lock")
public class RedisLockProperty {
    /**
     * 是否开启redis分布锁.
     */
    private Boolean enable = true;

    /**
     * 锁前缀.
     */
    private String registryKey = "system-lock";

    /**
     * 没有获到锁是否立即中断,true表示中断,false表示阻塞可重入锁.
     */
    private Boolean interrupt = false;

    /**
     * 手动锁键.
     */
    private String manualLockKey = "user-lock";
}
