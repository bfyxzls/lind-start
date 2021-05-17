package com.lind.common.proxy.anno;

import com.lind.common.proxy.DefaultMessageSender;
import com.lind.common.proxy.ServiceBeanDefinitionRegistry;
import com.lind.common.proxy.handler.DefaultEventHandler;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Import({ServiceBeanDefinitionRegistry.class, DefaultMessageSender.class, DefaultEventHandler.class})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
public @interface EnableServiceProvider {

    /**
     * 扫描的基础包，默认为启动类所在包.
     *
     * @return
     */
    @AliasFor("basePackages")
    String[] value() default {};

    /**
     * 扫描的基础包，默认为启动类所在包
     *
     * @return
     */
    @AliasFor("value")
    String[] basePackages() default {};
}
