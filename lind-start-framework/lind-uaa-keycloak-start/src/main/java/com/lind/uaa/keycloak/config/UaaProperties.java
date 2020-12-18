package com.lind.uaa.keycloak.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("jwt")
public class UaaProperties {
    /**
     * jwt算法密钥.
     */
    private String secret;
    /**
     * jwt超时时间(分).
     */
    private Long expiresAt;
    /**
     * URL白名单.
     */
    private String[] permitAll;

    /**
     * init.
     */
    public UaaProperties() {
        this.secret = "abc123";
        this.expiresAt = 60L;
        this.permitAll = new String[]{};
    }
}
