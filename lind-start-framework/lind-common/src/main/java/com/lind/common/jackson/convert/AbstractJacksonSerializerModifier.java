package com.lind.common.jackson.convert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.lind.common.util.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * jackson序列化转换器基类,子类以SerializerModifier结尾.
 */
@Slf4j
public abstract class AbstractJacksonSerializerModifier {
    /**
     * 将自己的转换器添加到转换器列表里.
     *
     * @param beanSerializerModifier
     * @return
     */
    protected MappingJackson2HttpMessageConverter callSelfSerializerModifier(
            BeanSerializerModifier beanSerializerModifier) {
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = converter.getObjectMapper();
        // 为mapper注册一个带有SerializerModifier的Factory,将所有的BeanSerializerModifier都从新装载进来,避免覆盖其它的
        List<String> beanNames = Arrays.stream(SpringContextUtils.getAllBeanNames())
                .filter(o -> o.endsWith("SerializerModifier"))
                .collect(Collectors.toList());
        List<BeanSerializerModifier> list = new ArrayList<>();
        for (String name : beanNames) {
            Object obj = SpringContextUtils.getBean(name);
            if (obj instanceof BeanSerializerModifier) {
                list.add((BeanSerializerModifier) SpringContextUtils.getBean(name));
            }
        }
        list.forEach((o) -> {
            mapper.setSerializerFactory(mapper.getSerializerFactory().withSerializerModifier(o));
        });
        mapper.setSerializerFactory(mapper.getSerializerFactory().withSerializerModifier(beanSerializerModifier));
        return converter;
    }
}
