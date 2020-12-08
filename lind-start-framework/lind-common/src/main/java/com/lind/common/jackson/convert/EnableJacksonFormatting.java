package com.lind.common.jackson.convert;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 开启json格式化.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({JacksonFormatSerializerModifierConfig.class, JacksonDateSerializerModifier.class, JacksonNullValueSerializerModifier.class})
public @interface EnableJacksonFormatting {
}