package com.lind.start.test.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Slf4j
@Configuration
public class JacksonScopeHttpMessageConverterConfig extends WebMvcConfigurationSupport {
    @Override
    //把重写的converter放入spring的converters里
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        log.info("加载JacksonScopeHttpMessageConverter转换器");
    }
}
