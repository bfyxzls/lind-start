package com.lind.start.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;
import java.util.Set;

@Configuration
public class JacksonScopeHttpMessageConverterConfig extends WebMvcConfigurationSupport {
    @Autowired
    Set<MappingJackson2HttpMessageConverter> mappingJackson2HttpMessageConverters;

    @Override
    //把重写的converter放入spring的converters里
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.addAll(mappingJackson2HttpMessageConverters);
    }


    /**
     * 解决swagger的404的问题
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
