package com.lind.hot.deploy.scope;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.util.List;

/**
 * scope授权范围序列化的限制.
 */
public class JacksonScopeHttpMessageConverter extends MappingJackson2HttpMessageConverter {
    public JacksonScopeHttpMessageConverter() {
        getObjectMapper().setSerializerFactory(
                getObjectMapper()
                        .getSerializerFactory()
                        .withSerializerModifier(new MyBeanSerializerModifier()));

    }

    /**
     * 忽略字段
     */
    public class IgnoreScope extends JsonSerializer<Object> {
        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
            jsonGenerator.writeNull();
        }
    }


    public class MyBeanSerializerModifier extends BeanSerializerModifier {
        @Override
        public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
            for (Object beanProperty : beanProperties) {
                BeanPropertyWriter writer = (BeanPropertyWriter) beanProperty;
                Class<?> clazz = writer.getType().getRawClass();
                if (writer.getAnnotation(ScopeSet.class) != null) {
                    writer.assignSerializer(new IgnoreScope());
                }
            }
            return beanProperties;
        }
    }
}
