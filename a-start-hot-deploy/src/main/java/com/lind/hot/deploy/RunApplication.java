package com.lind.hot.deploy;

import cn.hutool.core.io.FileUtil;
import com.lind.common.jackson.convert.EnableJacksonFormatting;
import com.lind.common.util.JarClassLoader;
import com.lind.hot.deploy.scope.EnableScoping;
import com.lind.hot.deploy.spi.SpiRun;
import com.lind.interfaces.HelloProvider;
import com.lind.interfaces.HelloProviderFactory;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

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
        SpiRun.run();
        String path = "file:/d:/plugins/a-start-hot-dependency-1.0.0.jar";
        JarClassLoader.joinJar(RunApplication.class, path);
        SpringApplication.run(RunApplication.class, args);

    }

    public static <U extends HelloProviderFactory> U getServiceProvider(Class<U> clazz, String name, ClassLoader classLoader) {
        ServiceLoader<U> load = ServiceLoader.load(clazz, classLoader);
        List<U> list = new ArrayList<>();
        Iterator<U> loadIterator = load.iterator();
        while (loadIterator.hasNext()) {
            if (loadIterator.next().getName().equals(name)) {
                return loadIterator.next();
            }
        }
        return null;
    }

    @SneakyThrows
    @GetMapping("list")
    public ResponseEntity list() {

        List<String> fileList = new ArrayList<>();
        for (File file : FileUtil.ls(path)) {
            fileList.add(file.getName());
        }
        HelloProviderFactory helloProviderFactory = getServiceProvider(HelloProviderFactory.class, "EmailProviderFactory", this.getClass().getClassLoader());

        return ResponseEntity.ok(helloProviderFactory.create().login());
    }

    @GetMapping("load/{name}")
    public ResponseEntity load(@PathVariable String name) {
        HelloProvider account = JarClassLoader.joinOuterJarClass(HelloProvider.class, path + "/a-start-hot-dependency-1.0.0.jar", "com.lind.hot.EmailHelloProvider");
        Object ret = account.login();
        return ResponseEntity.ok(ret);
    }
}
