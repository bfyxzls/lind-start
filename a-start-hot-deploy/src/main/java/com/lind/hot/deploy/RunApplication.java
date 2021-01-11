package com.lind.hot.deploy;

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
    }


    @SneakyThrows
    @GetMapping("list")
    public ResponseEntity list() {
        String path = "d:\\plugins\\a-start-hot-dependency-1.0.0.jar";

//        CarHelloProviderFactory carHelloProviderFactory = SpiFactory.getProviderFactory(CarHelloProviderFactory.class,
//                "BusCarHelloProvider",
//                this.getClass().getClassLoader());
//        carHelloProviderFactory.create().start();
//
//        CarHelloProviderFactory carHelloProviderFactory2 = SpiFactory.getProviderFactory(CarHelloProviderFactory.class,
//                "PrivateCarHelloProvider",
//                this.getClass().getClassLoader());
//        carHelloProviderFactory2.create().start();

        SpiFactory.addFile(path, this.getClass().getClassLoader());
        ProviderFactory providerFactory = SpiFactory.getProviderFactory("EmailHelloProvider", this.getClass().getClassLoader());
        return ResponseEntity.ok(providerFactory.create().login());
    }


}
