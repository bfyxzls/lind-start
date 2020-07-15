package com.lind.common.logger;

import com.lind.common.exception.LindException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(LoggerProperties.class)
public class LoggerConfigure {
    LoggerProperties loggerProperties;

    public LoggerConfigure(LoggerProperties loggerProperties) {
        this.loggerProperties = loggerProperties;
    }

    /**
     * 默认实现.
     *
     * @return
     */
    @Bean
    public Logger defaultLogger() {
        return new EsLogger();
    }

    /**
     * Logger对象的生成，它做了conditional，当属性enable为false时，Logger对象不会被初始化
     *
     * @return
     */
    @Bean
    @ConditionalOnProperty(value = "lind.logger.enable", havingValue = "true", matchIfMissing = true)
    public Logger logger() {
        switch (loggerProperties.getType()) {
            case "Console":
                return new EsLogger();
            case "File":
                return new FileLogger();
            default:
                throw new LindException("不能的日志类型");
        }
    }
}
