package com.lind.uaa.jwt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("jwt")
public class JwtConfig {
  /**
   * jwt算法密钥.
   */
  private String secret;

  /**
   * 每分钟容许登录失败的次数.
   */
  private Integer failLimit;

  /**
   * jwt超时时间(分).
   */
  private Long expiresAt;

  /**
   * 自动刷新token超时时间(分).
   */
  private Long refreshTokenExpiresAt;

  /**
   * URL白名单.
   */
  private String[] permitAll;

  /**
   * init.
   */
  public JwtConfig() {
    this.secret = "abc123";
    this.expiresAt = 60L;
    this.refreshTokenExpiresAt = 50L;
    this.failLimit = 5;
    this.permitAll = new String[]{};
  }

}
