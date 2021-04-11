package com.lind.start.test;

import com.lind.common.util.SpringContextUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication springApplication=new SpringApplication(TestApplication.class);
        ConfigurableApplicationContext ctx = springApplication.run(args);
        SpringContextUtils.setApplicationContext(ctx);
    }
}
