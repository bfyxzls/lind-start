package com.lind.redis.cache.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置实体
 */
@ConfigurationProperties(prefix = "lind.lettuce.redis")
public class LettuceRedisProperties {

    /**
     * 是否开启Lettuce Redis
     */
    private Boolean enable = true;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }


}
