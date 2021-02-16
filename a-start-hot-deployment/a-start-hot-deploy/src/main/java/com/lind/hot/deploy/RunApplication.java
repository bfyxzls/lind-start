package com.lind.hot.deploy;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RunApplication extends SpringBootServletInitializer {

    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(RunApplication.class, args);
        // SpiFactory.watchDir("d:\\plugins");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(RunApplication.class);
    }

    @GetMapping("hello")
    public ResponseEntity hello() {
        return ResponseEntity.ok("hello world");
    }


}
