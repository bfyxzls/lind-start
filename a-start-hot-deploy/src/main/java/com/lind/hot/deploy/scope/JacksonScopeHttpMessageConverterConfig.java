package com.lind.hot.deploy.scope;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Slf4j
public class JacksonScopeHttpMessageConverterConfig extends WebMvcConfigurationSupport {


    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, new JacksonScopeHttpMessageConverter());
    }
}