package com.lind.keycloak;

import com.lind.uaa.keycloak.scope.EnableScoping;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableScoping
public class KeycloakApplication {
    public static void main(String[] args) {
        SpringApplication.run(KeycloakApplication.class, args);
    }
}
