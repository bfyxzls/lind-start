package com.lind.logger.config;

import com.lind.logger.aspect.LogRecordAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggerConfig {
  @Bean
  public LogRecordAspect logRecordAspect() {
    return new LogRecordAspect();
  }
}
