package com.lind.common.jackson.convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class JacksonDateFormatSerializerModifierConfig extends AbstractJacksonSerializerModifier {
    @Autowired
    JacksonDateSerializerModifier jacksonDateSerializerModifier;
    @Bean
    @DependsOn("springContextUtils")
    public MappingJackson2HttpMessageConverter jacksonDateSerializerModifierConvert() {
        return super.callSelfSerializerModifier(jacksonDateSerializerModifier);
    }

}
