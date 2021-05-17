package com.lind.common.proxy.anno;

import com.lind.common.proxy.handler.DefaultEventHandler;
import com.lind.common.proxy.handler.EventHandler;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface MessageSend {

    /**
     * 发送的主题，支持springEL表达式
     *
     * @return
     */
    String topic();

    /**
     * 消息发送成功处理函数
     *
     * @return
     */
    Class<? extends EventHandler> eventHandler() default DefaultEventHandler.class;


}
