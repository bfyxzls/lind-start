package com.lind.hot.deploy;

import cn.hutool.core.io.FileUtil;
import com.lind.hot.deploy.spi.CarHelloProviderFactory;
import com.lind.spi.DynamicClassLoader;
import com.lind.spi.ProviderFactory;
import com.lind.spi.SpiFactory;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class RunApplication {
    @Value("${plugins.helloProvider.path}")
    String path;

    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(RunApplication.class, args);
    }

    @SneakyThrows
    @GetMapping("car")
    public ResponseEntity car(String name) {
        //BusCarHelloProvider
        CarHelloProviderFactory carHelloProviderFactory = SpiFactory.getProviderFactory(CarHelloProviderFactory.class,
                name,
                this.getClass().getClassLoader());
        carHelloProviderFactory.create().start();

        return ResponseEntity.ok(carHelloProviderFactory.create().login());
    }

    /**
     * 初始化时，应该对插件读出后，写到一个Map里，插件名称是key，DynamicClassLoader是value
     * @param path
     * @return
     */
    @SneakyThrows
    List<DynamicClassLoader> initClassLoader(String path) {
        List<DynamicClassLoader> dynamicClassLoaderList = new ArrayList<>();
        for (File file : FileUtil.loopFiles(path)) {
            URL url = file.toURI().toURL();
            DynamicClassLoader dynamicClassLoader = new DynamicClassLoader(new URL[]{url}, this.getClass().getClassLoader());
            dynamicClassLoaderList.add(dynamicClassLoader);
        }
        return dynamicClassLoaderList;
    }

    @SneakyThrows
    @GetMapping("list")
    public ResponseEntity list(String name) {
        //EmailHelloProvider
        String path = "d:\\plugins";
        for (DynamicClassLoader dynamicClassLoader : initClassLoader(path)) {
            try {
                ProviderFactory providerFactory = SpiFactory.getProviderFactory(name, dynamicClassLoader);
                if (providerFactory != null) {
                    return ResponseEntity.ok(providerFactory.create().login());
                }
            } catch (RuntimeException ex) {

            }

        }
        return ResponseEntity.ok("no find provider..." + name);
    }


}
