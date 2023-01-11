package com.lind.common.bean.factory.core;

import lombok.Data;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

@Data
public class SendFactoryBean<T> implements FactoryBean<T> {

	private Class<T> interfaceType;

	private BeanFactory beanFactory;

	@Override
	public T getObject() throws Exception {
		InvocationHandler handler = new SendProxy(beanFactory);
		return (T) Proxy.newProxyInstance(interfaceType.getClassLoader(), new Class[] { interfaceType }, handler);
	}

	@Override
	public Class<?> getObjectType() {
		return interfaceType;
	}

}
