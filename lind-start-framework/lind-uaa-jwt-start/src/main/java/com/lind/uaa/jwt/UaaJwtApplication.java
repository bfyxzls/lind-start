package com.lind.uaa.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class UaaJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(UaaJwtApplication.class, args);
    }
}
