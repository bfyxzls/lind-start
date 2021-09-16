package com.lind.proxy.core;

import com.lind.proxy.service.Do;
import org.springframework.beans.factory.BeanFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProviderProxy implements InvocationHandler {
    private BeanFactory applicationContext;

    public ProviderProxy(BeanFactory applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Do doAction = applicationContext.getBean(Do.class);
        doAction.send(args[0].toString());
        System.out.println("ProviderProxy a object.");
        return null;
    }
}
