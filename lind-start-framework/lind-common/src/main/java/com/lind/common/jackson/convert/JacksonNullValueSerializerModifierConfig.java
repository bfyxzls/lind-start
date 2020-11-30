package com.lind.common.jackson.convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class JacksonNullValueSerializerModifierConfig extends AbstractJacksonSerializerModifier {
    @Autowired
    JacksonNullValueSerializerModifier jacksonNullValueSerializerModifier;
    @Bean
    @DependsOn("springContextUtils")
    public MappingJackson2HttpMessageConverter jacksonNullValueSerializerModifierConvert() {
        return super.callSelfSerializerModifier(jacksonNullValueSerializerModifier);
    }

}
