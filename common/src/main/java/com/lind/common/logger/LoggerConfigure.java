package com.lind.common.logger;

import com.lind.common.exception.LindException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(LoggerProperties.class)
@ConditionalOnClass(Logger.class)
public class LoggerConfigure {
    LoggerProperties loggerProperties;

    public LoggerConfigure(LoggerProperties loggerProperties) {
        this.loggerProperties = loggerProperties;
    }

    @Bean
    @ConditionalOnProperty(value = "lind.logger.enable", havingValue = "true", matchIfMissing = true)
    public Logger logger() {
        switch (loggerProperties.getType()) {
            case "Console":
                return new ConsoleLogger();
            case "File":
                return new FileLogger();
            default:
                throw new LindException("不能的日志类型");
        }
    }
}
