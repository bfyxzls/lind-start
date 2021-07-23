package com.lind.lindmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LindManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LindManagerApplication.class, args);
    }

}
