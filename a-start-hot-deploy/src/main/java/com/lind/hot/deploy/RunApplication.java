package com.lind.hot.deploy;

import com.lind.common.util.JarClassLoader;
import com.lind.hot.deploy.dto.UserDTO;
import com.lind.hot.deploy.scope.EnableScoping;
import com.lind.interfaces.Hello;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.TimeZone;

@SuppressWarnings("deprecation")
@SpringBootApplication
@RestController
@EnableScoping
public class RunApplication {


    public static void main(String[] args) {
        SpringApplication.run(RunApplication.class, args);
    }

    @GetMapping(path = "/test")
    public ResponseEntity test() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("lind");
        userDTO.setEmail("123");
        TimeZone time = TimeZone.getTimeZone("Etc/GMT-8");  //转换为中国时区

        TimeZone.setDefault(time);
        userDTO.setBirthday(new Date());
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("dy")
    public ResponseEntity dy() {
        Hello account = JarClassLoader.joinOuterJarClass("a-start-hot-dependency-1.0.0.jar", Hello.class, "com.lind.hot.HelloImpl");
        Object ret = account.password();
        return ResponseEntity.ok(ret);
    }

}
