package com.lind.uaa.keycloak.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("uaa")
public class UaaProperties {
    /**
     * jwt算法密钥.
     */
    private String callbackUri;
    /**
     * jwt超时时间(分).
     */
    private Long redirectUri;
    /**
     * URL白名单.
     */
    private String[] permitAll;

    /**
     * 除了permitAll里的地址，其它地址需要有allowRoles包含的角色，才能访问.
     */
    private String[] allowRoles;

}
