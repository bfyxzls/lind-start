package com.lind.common.proxy.register;

import com.lind.common.proxy.anno.MessageSend;
import com.lind.common.proxy.handler.MessageProviderHandler;
import com.lind.common.proxy.handler.SuccessSendHandler;
import com.lind.common.proxy.service.MessageService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
@Data
public class MessageProxy implements InvocationHandler {
    private BeanFactory applicationContext;
    private MessageService messageService;

    public MessageProxy(BeanFactory applicationContext) {
        this.applicationContext = applicationContext;
        messageService = applicationContext.getBean(MessageService.class);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (args.length != 1) {
            throw new IllegalArgumentException("方法只能有一个参数");
        }
        Object arg = args[0];
        if (method.isAnnotationPresent(MessageSend.class)) {
            MessageSend annotation = method.getAnnotation(MessageSend.class);
            Class<? extends SuccessSendHandler> sccessHandlerType = annotation.successSendHandler();
            SuccessSendHandler successHandler = applicationContext.getBean(sccessHandlerType);

            Class<? extends MessageProviderHandler> messageProviderHandlerType = annotation.messageProviderHandler();
            MessageProviderHandler messageProviderHandler = applicationContext.getBean(messageProviderHandlerType.getSimpleName(), MessageProviderHandler.class);

            int types = annotation.type();
            messageService.send(arg, messageProviderHandler, successHandler);
        }
        return null;
    }


}
