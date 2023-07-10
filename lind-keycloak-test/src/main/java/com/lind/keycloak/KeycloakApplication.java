package com.lind.keycloak;

import com.pkulaw.uaa.keycloak.config.EnableKeycloakSessionState;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lind
 * @date 2023/7/10 17:44
 * @since 1.0.0
 */
@SpringBootApplication
@EnableKeycloakSessionState // 自动状态同步，白名单的页面会进行跳转
public class KeycloakApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeycloakApplication.class, args);
	}

}
