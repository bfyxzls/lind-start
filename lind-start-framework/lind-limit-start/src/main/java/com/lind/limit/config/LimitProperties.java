package com.lind.limit.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 配置属性,不会自动注册.
 */
@Data
@ConfigurationProperties(prefix = "lind.ratelimit")
public class LimitProperties {

    /**
     * 是否开启全局限流.
     */
    private Boolean enable = false;

    /**
     * 限制请求个数.
     */
    private Integer limit = 100;

    /**
     * 每单位时间内（毫秒）.
     */
    private Integer timeout = 1000;
}