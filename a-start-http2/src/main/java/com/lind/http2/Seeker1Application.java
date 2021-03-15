package com.lind.http2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.undertow.UndertowBuilderCustomizer;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;

@SpringBootApplication
public class Seeker1Application implements WebServerFactoryCustomizer<UndertowServletWebServerFactory> {
    public static void main(String[] args) {
        SpringApplication.run(Seeker1Application.class, args);
    }

    /**
     * auto enable http 8080.
     * @param factory
     */
    @Override
    public void customize(UndertowServletWebServerFactory factory) {
        factory.addBuilderCustomizers((UndertowBuilderCustomizer) builder -> {
            builder.addHttpListener(8088, "0.0.0.0");
        });
    }
}
