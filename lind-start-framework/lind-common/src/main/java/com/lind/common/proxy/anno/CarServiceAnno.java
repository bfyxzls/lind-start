package com.lind.common.proxy.anno;

import com.lind.common.proxy.demo.CarHandler;
import com.lind.common.proxy.demo.DefaultCarHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Inherited
public @interface CarServiceAnno {
    Class<? extends CarHandler> onAfter() default DefaultCarHandler.class;
}
