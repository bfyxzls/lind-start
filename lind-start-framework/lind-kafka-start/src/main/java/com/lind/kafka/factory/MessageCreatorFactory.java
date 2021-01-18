package com.lind.kafka.factory;

import com.lind.kafka.proxy.MessageSenderProxy;
import lombok.Data;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @Description TODO
 * @date 2020/7/9 22:46
 **/
@Data
public class MessageCreatorFactory<T> implements FactoryBean<T> {

    private Class<T> interfaceType;


    private BeanFactory applicationContext;


    @Override
    public T getObject() throws Exception {
        InvocationHandler invocationHandler = new MessageSenderProxy(applicationContext);
        return (T) Proxy.newProxyInstance(interfaceType.getClassLoader(),
                new Class[]{interfaceType}, invocationHandler);

    }

    @Override
    public Class<?> getObjectType() {
        return interfaceType;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
