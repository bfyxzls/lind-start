package com.lind.start.test;

import com.lind.common.aspect.timer.EnableRunTime;
import com.lind.common.jackson.convert.EnableJacksonFormatting;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@EnableRunTime
@EnableJacksonFormatting
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
