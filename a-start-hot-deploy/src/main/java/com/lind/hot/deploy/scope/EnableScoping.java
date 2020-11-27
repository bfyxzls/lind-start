package com.lind.hot.deploy.scope;

import org.checkerframework.framework.qual.ImplicitFor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启Uaa的Scope授权功能.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(JacksonScopeHttpMessageConverterConfig.class)
public @interface EnableScoping {
}
