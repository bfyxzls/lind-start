package com.lind.hot.deploy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * springboot默认序列化jackson的日期格式化.
 */
@Configuration
public class JacksonDateHttpMessageConverterConfig extends DelegatingWebMvcConfiguration {
    @Override
    public void configureMessageConverters(final List<HttpMessageConverter<?>> messageConverters) {
        // Add a MappingJackson2HttpMessageConverter so that
        // objectMapper.writeFiltered
        // is using the objectMapper configured with the needed Mixin

        messageConverters.add(new JacksonDateHttpMessageConverter());

        //
        addDefaultHttpMessageConverters(messageConverters);
        super.configureMessageConverters(messageConverters);
    }
}