package com.lind.start.test;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class TestApplication {
    public static void main(String[] args) {
         SpringApplication.run(TestApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            System.out.println("spring bean count：" + beanNames.length);
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println("-------" + beanName);
            }
        };
    }
}
