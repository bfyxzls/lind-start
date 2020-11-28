package com.lind.uaa.keycloak.scope;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JacksonScopeHttpMessageConverterConfig extends WebMvcConfigurationSupport {
    @Override
    //把重写的converter放入spring的converters里
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        List<MappingJackson2HttpMessageConverter> list = new ArrayList<>();
        list.add(new JacksonScopeHttpMessageConverter());
        converters.addAll(list);

    }

}
