package com.lind.common.lock.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "redis.lock")
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
     * 没有获到锁是否立即中断,true表示中断,false表示阻塞.
     */
    private Boolean interrupt = true;

    /**
     * 是否自动释放锁.
     */
    private Boolean autoRelease = true;

    /**
     * 手动锁键.
     */
    private String manualLockKey = "userLock";
}
