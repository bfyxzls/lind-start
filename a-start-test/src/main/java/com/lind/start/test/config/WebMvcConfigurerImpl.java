package com.lind.start.test.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.lind.common.limit.LimitRaterInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Configuration
@EnableWebMvc //覆盖默认的配置
public class WebMvcConfigurerImpl implements WebMvcConfigurer {
    @Autowired
    LimitRaterInterceptor limitRaterInterceptor;

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
        simpleModule.addSerializer(BigDecimal.class, new MoneySerialize());

        // double保留两位小数
        simpleModule.addSerializer(Double.class, new DoubleSerialize());
        simpleModule.addSerializer(Double.TYPE, new DoubleSerialize());

        // 时间格式化
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));//只能是一个日期格式化，多个会复盖

        // 设置格式化内容
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        converters.add(0, jackson2HttpMessageConverter);

        objectMapper.registerModule(simpleModule);
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        converters.add(jackson2HttpMessageConverter);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        InterceptorRegistration ir = registry.addInterceptor(limitRaterInterceptor);
        // 配置拦截的路径
        ir.addPathPatterns("/**");
        // 配置不拦截的路径
        ir.excludePathPatterns("/no-get");
    }

    /**
     * money serializer.
     */
    public class MoneySerialize extends JsonSerializer<Object> {
        //修改要除的数据
        final BigDecimal TEMP = BigDecimal.valueOf(1000000L);

        @Override
        public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (value != null) {
                BigDecimal bigDecimal = new BigDecimal(value.toString());
                gen.writeNumber(bigDecimal.divide(TEMP, 4, RoundingMode.DOWN));
            }
        }
    }

    /**
     * double serializer.
     */
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
