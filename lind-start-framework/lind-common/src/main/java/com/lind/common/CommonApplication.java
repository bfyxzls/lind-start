package com.lind.common;

import com.lind.common.locale.LocaleMessageUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CommonApplication {


    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class, args);
    }

    @GetMapping("version")
    public ResponseEntity version() {
        return ResponseEntity.ok("1.0.0");
    }

    @GetMapping("m")
    public String m() {
       return  LocaleMessageUtils.getMessage("title");
    }
}
