package com.lind.lindmanager;

import com.lind.common.enums.NameValueEnumConverterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	NameValueEnumConverterFactory enumsConvertorFactory;

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverterFactory(enumsConvertorFactory);
	}

}
