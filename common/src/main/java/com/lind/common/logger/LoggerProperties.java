package com.lind.common.logger;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 日志配置节点.
 */
@ConfigurationProperties(prefix = "lind.logger")
public class LoggerProperties {
    private String type="Console";
    private String level="Debug";
    private Boolean enable;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}

