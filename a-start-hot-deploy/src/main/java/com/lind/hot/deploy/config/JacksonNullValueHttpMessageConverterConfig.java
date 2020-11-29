package com.lind.hot.deploy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * springboot默认序列化jackson的空值默认值.
 */
@Configuration
@EnableWebMvc
public class JacksonNullValueHttpMessageConverterConfig extends WebMvcConfigurationSupport {

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(1, new JacksonNullValueHttpMessageConverter());
    }


}
