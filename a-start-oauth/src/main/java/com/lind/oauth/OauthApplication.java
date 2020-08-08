package com.lind.oauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
@RefreshScope
@EnableAuthorizationServer
@MapperScan("com.lind.uaa.dao")

public class OauthApplication {


    public static void main(String[] args) {
        SpringApplication.run(OauthApplication.class, args);

    }

    @GetMapping("hello")
    public ResponseEntity hello() {
        return ResponseEntity.ok("hello world!");
    }
}
