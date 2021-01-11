package com.lind.hot.deploy;

import cn.hutool.core.io.FileUtil;
import com.lind.hot.deploy.spi.CarHelloProviderFactory;
import com.lind.spi.SpiFactory;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class RunApplication {
    @Value("${plugins.helloProvider.path}")
    String path;

    @SneakyThrows
    public static void main(String[] args) {
        String path = "file:/d:/plugins/a-start-hot-dependency-1.0.0.jar";
        SpiFactory.joinJar(RunApplication.class, path);
        SpringApplication.run(RunApplication.class, args);

    }


    @SneakyThrows
    @GetMapping("list")
    public ResponseEntity list() {

        List<String> fileList = new ArrayList<>();
        for (File file : FileUtil.ls(path)) {
            fileList.add(file.getName());
        }

        CarHelloProviderFactory carHelloProviderFactory = SpiFactory.getProviderFactory(CarHelloProviderFactory.class,
                "BusCarHelloProvider",
                this.getClass().getClassLoader());
        carHelloProviderFactory.create().start();

        CarHelloProviderFactory carHelloProviderFactory2 = SpiFactory.getProviderFactory(CarHelloProviderFactory.class,
                "PrivateCarHelloProvider",
                this.getClass().getClassLoader());
         carHelloProviderFactory2.create().start();
        return ResponseEntity.ok("ok");
    }


}
