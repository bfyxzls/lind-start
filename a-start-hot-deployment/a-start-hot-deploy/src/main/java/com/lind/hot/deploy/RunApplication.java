package com.lind.hot.deploy;

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

@SpringBootApplication
@RestController
public class RunApplication {
    @Value("${plugins.helloProvider.path}")
    String path;

    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(RunApplication.class, args);
        SpiFactory.watchDir("d:\\plugins");
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


    @SneakyThrows
    @GetMapping("list")
    public ResponseEntity list(String name) {
        //EmailHelloProvider
        for (DynamicClassLoader dynamicClassLoader : SpiFactory.dynamicClassLoaders) {
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
