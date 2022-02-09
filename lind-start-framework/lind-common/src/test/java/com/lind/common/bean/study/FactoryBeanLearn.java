package com.lind.common.bean.study;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 建立一个bean对象
 */
@Slf4j
public class FactoryBeanLearn implements FactoryBean {
  @Override
  public Object getObject() throws Exception {
    //这个Bean是我们自己new的，这里我们就可以控制Bean的创建过程了
    InvocationHandler handler = new InvocationHandler() {
      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("dynamic");
        return method.invoke(args);

      }
    };
    return Proxy.newProxyInstance(new FactoryBeanServiceImpl().getClass().getClassLoader(),
        new Class[]{FactoryBeanService.class}, handler);


  }

  @Override
  public Class<?> getObjectType() {
    return FactoryBeanService.class;
  }

  @Override
  public boolean isSingleton() {
    return true;
  }
}
