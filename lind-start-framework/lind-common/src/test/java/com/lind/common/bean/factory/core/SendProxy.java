package com.lind.common.bean.factory.core;

import lombok.Data;
import org.springframework.beans.factory.BeanFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Data
public class SendProxy implements InvocationHandler {

	private BeanFactory applicationContext;

	public SendProxy(BeanFactory applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("SendProxy...");
		if (method.getName().equals("insert")) {
			System.out.println("insert...");

		}
		if (method.getName().equals("delete")) {
			System.out.println("delete...");

		}
		return null;
	}

}
