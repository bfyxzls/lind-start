package com.lind.start.test.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

@Configuration
public class JsonConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        /**
         * 序列换成Json时,将所有的Long变成String
         * 因为js中得数字类型不能包括所有的java Long值
         */
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);

        // 所有的double类型返回保留三位小数
        simpleModule.addSerializer(Double.class, new Object6Serialize());
        simpleModule.addSerializer(Double.TYPE,new Object6Serialize());

        simpleModule.addSerializer(Double.class, new DoubleSerialize());
        simpleModule.addSerializer(Double.TYPE,new DoubleSerialize());

        objectMapper.registerModule(simpleModule);
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        converters.add(jackson2HttpMessageConverter);
    }

    /**
     * 描述：金额数值序列化
     * 1.分 -> 万 当前数据除以6个0： 1000000 -> 1
     */
    public class Object6Serialize extends JsonSerializer<Object> {
        //修改要除的数据
        final BigDecimal TEMP = BigDecimal.valueOf(1000000L);

        @Override
        public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (value != null) {
                BigDecimal bigDecimal = new BigDecimal(value.toString());
                //参考该方法 第二个参数是几就保留几位小数 第三个参数 参考 RoundingMode.java
                gen.writeNumber(bigDecimal.divide(TEMP, 4, RoundingMode.DOWN));
            }
        }
    }

    public class DoubleSerialize extends JsonSerializer<Double> {

        private DecimalFormat df = new DecimalFormat("##.00");

        @Override
        public void serialize(Double value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException, JsonProcessingException {
            if (value != null) {
                gen.writeString(df.format(value));
            }
        }
    }


}
