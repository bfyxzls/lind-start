package com.lind.nacos;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@RefreshScop
@ConfigurationProperties(prefix = "user")
@Component
@Data
public class TestConfig {
  private String name;
}