package com.lind.kafka.anno;

import com.lind.kafka.handler.DefaultFailureHandler;
import com.lind.kafka.handler.DefaultSuccessHandler;
import com.lind.kafka.handler.FailureHandler;
import com.lind.kafka.handler.SuccessHandler;

import java.lang.annotation.*;

/**
 * @Description 发送kafka消息注解
 * @date 2020/7/7 11:20
 **/
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
     * 发送消息错误处理函数
     *
     * @return
     */
    Class<? extends FailureHandler> failureHandler() default DefaultFailureHandler.class;

    /**
     * 消息发送成功处理函数
     *
     * @return
     */
    Class<? extends SuccessHandler> successHandler() default DefaultSuccessHandler.class;


}
