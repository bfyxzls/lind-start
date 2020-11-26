package com.lind.hot.deploy;

import com.lind.interfaces.Hello;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RunApplication {


    public static void main(String[] args) {
        JarClassLoader jarClassLoader=new JarClassLoader();
        Hello account = jarClassLoader.joinOuterJarClass("file:///d:/a-start-hot-dependency-1.0.0.jar", Hello.class, "HelloImpl");
        Object ret = account.password();
        SpringApplication.run(RunApplication.class, args);
    }


    @GetMapping("dy")
    public ResponseEntity dy() {
        JarClassLoader jarClassLoader=new JarClassLoader();
        Hello account = jarClassLoader.joinOuterJarClass("file:///d:/a-start-hot-dependency-1.0.0.jar", Hello.class, "com.lind.hot.HelloImpl");
        Object ret = account.password();
        return ResponseEntity.ok(ret);
    }

}
