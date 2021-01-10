package com.lind.hot.deploy;

import cn.hutool.core.io.FileUtil;
import com.lind.common.jackson.convert.EnableJacksonFormatting;
import com.lind.common.util.JarClassLoader;
import com.lind.hot.deploy.scope.EnableScoping;
import com.lind.spi.ProviderFactory;
import com.lind.spi.SpiFactory;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@EnableJacksonFormatting
@EnableScoping
public class RunApplication {
    final static Logger LOGGER = LoggerFactory.getLogger(JarClassLoader.class);
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
        ProviderFactory helloProviderFactory = SpiFactory.getProviderFactory(
                "EmailHelloProvider",
                this.getClass().getClassLoader());

        return ResponseEntity.ok(helloProviderFactory.create().login());
    }


}
