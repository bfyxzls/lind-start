package com.lind.start.test;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
@Slf4j
public class TestApplication {
    @SneakyThrows
    public static void main(String[] args) throws IOException {
        SpringApplication.run(TestApplication.class, args);
    }

}
