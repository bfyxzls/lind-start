package com.lind.testshade;

import com.lind.testshade.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@MapperScan("com.lind.testshade.mapper")
@RestController
public class ShadeApplication {
    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(ShadeApplication.class, args);
    }

    @GetMapping("users")
    public ResponseEntity users() {
        return ResponseEntity.ok(userService.getByName("lvzhuqing"));
    }
}
