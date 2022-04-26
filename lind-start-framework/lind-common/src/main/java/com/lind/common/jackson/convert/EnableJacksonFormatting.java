package com.lind.common.jackson.convert;

import com.lind.common.enums.serializer.NameValueEnumSerializer;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启json格式化.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({NameValueEnumSerializer.class,
        JacksonDateSerializerModifier.class,
        JacksonNullValueSerializerModifier.class})
public @interface EnableJacksonFormatting {
}
