package com.lind.kafka.proxy;

import com.lind.kafka.anno.MessageSend;
import com.lind.kafka.entity.MessageEntity;
import com.lind.kafka.handler.FailureHandler;
import com.lind.kafka.handler.SuccessHandler;
import com.lind.kafka.sender.MessageSender;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.EmbeddedValueResolver;
import org.springframework.util.Assert;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
  * @Description 动态代理，需要注意的是，这里用到的是JDK自带的动态代理，代理对象只能是接口，不能是类
 * @date 2020/7/9 22:55
 **/
@Slf4j
@Data
public class MessageSenderProxy<T> implements InvocationHandler {




    private BeanFactory applicationContext;

    private MessageSender messageSender;

    public MessageSenderProxy(BeanFactory applicationContext) {
        this.applicationContext = applicationContext;

        this.messageSender = applicationContext.getBean(MessageSender.class);
    }


    /**
     * 解析springEL表达式
     *
     * @param myString
     * @return
     */
    public String evaluateExpression(String myString) {
        StringValueResolver str = new EmbeddedValueResolver((ConfigurableBeanFactory) applicationContext);
        return str.resolveStringValue(myString);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        if (args.length != 1) {
            throw new IllegalArgumentException("发送的消息只能是MessageEntity的子类且只能有一个参数");
        }
        Object arg = args[0];
        if (!MessageEntity.class.isAssignableFrom(arg.getClass())) {
            throw new IllegalArgumentException("参数必须是MessageEntity的子类");
        }

        if (method.isAnnotationPresent(MessageSend.class)) {
            MessageSend annotation = method.getAnnotation(MessageSend.class);
            String topic = annotation.topic();

            Assert.hasText(topic, "发送主题不能为空，支持springEL表达式，请重新设置");
            //解析springEL表达式
            topic = evaluateExpression(topic);

            Class<? extends FailureHandler> failureHandlerClass = annotation.failureHandler();

            FailureHandler failureHandler = applicationContext.getBean(failureHandlerClass);


            Class<? extends SuccessHandler> successHandlerClass = annotation.successHandler();

            SuccessHandler successHandler = applicationContext.getBean(successHandlerClass);

            //if (log.isDebugEnabled()) {
            log.info("发送消息：topic:{},消息体：{}", topic, arg);
            //}

            messageSender.send(topic, (MessageEntity) arg, successHandler, failureHandler);
        } else {
            throw new IllegalArgumentException("方法必须包换@MessageSend注解");
        }

        return null;
    }
}
