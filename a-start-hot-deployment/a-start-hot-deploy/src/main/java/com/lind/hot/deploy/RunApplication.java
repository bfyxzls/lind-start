package com.lind.hot.deploy;

import com.lind.hot.deploy.spi.CarHelloProviderFactory;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ServiceLoader;

@SpringBootApplication
@RestController
public class RunApplication extends SpringBootServletInitializer {


    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(RunApplication.class, args);
        // SpiFactory.watchDir("d:\\plugins");

    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(RunApplication.class);
    }

    @GetMapping("hello")
    public void hello() {

        ServiceLoader<CarHelloProviderFactory> carHelloProviderFactories = ServiceLoader.load(CarHelloProviderFactory.class);
        for (CarHelloProviderFactory u : carHelloProviderFactories) {
            u.create().start();
        }
    }


}
