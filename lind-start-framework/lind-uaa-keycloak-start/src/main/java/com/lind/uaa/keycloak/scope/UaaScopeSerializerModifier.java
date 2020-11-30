package com.lind.uaa.keycloak.scope;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.lind.uaa.keycloak.config.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.List;

@Slf4j
public class UaaScopeSerializerModifier extends BeanSerializerModifier {
    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
        for (Object beanProperty : beanProperties) {
            BeanPropertyWriter writer = (BeanPropertyWriter) beanProperty;
            if (writer.getAnnotation(ScopeSet.class) != null) {
                String value = writer.getAnnotation(ScopeSet.class).value();
                if (StringUtils.isNoneBlank(value)) {
                    Object obj = SecurityUser.getCurrentPrincipal();
                    log.info("UaaScopeSerializerModifier:{}->{}...", writer.getName(), value);
                    writer.assignSerializer(new IgnoreScope());
                }

            }
        }
        return beanProperties;
    }

    /**
     * 忽略字段
     */
    public static class IgnoreScope extends JsonSerializer<Object> {
        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
            jsonGenerator.writeNull();
        }
    }
}