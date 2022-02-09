package com.lind.common.bean.study;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Demo {
    @Autowired
    FactoryBeanService factoryBeanService;

    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(FactoryBeanLearnConfig.class);
        context.getBean(FactoryBeanService.class).testFactoryBean();
    }

    @Test
    public void proxyTest(){
        proxy();
    }


    /**
     * 代理模式
     * newProxyInstance 返回接口 调用接口实现类的任何方法都会调用代理模式的invoke
     */
      static void proxy() {
        final ICat cat = new Cat();

        ICat proxyCat=(ICat) Proxy.newProxyInstance(Demo.class.getClassLoader(), cat.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                if (method.getName().equals("run")) {
                    System.out.println("猫抓老鼠");
                    return method.invoke(cat, objects);
                }
                return method.invoke(cat, objects);
            }
        });
          cat.run();
    }

    /**
     * 装饰者模式
     * 1.装饰者和被装饰者实现或继承相同的接口或类
     * 2.装饰者持有被装饰者的引用
     *
     */

    static class Decorate implements ICat{

        ICat mICat;

        public Decorate(ICat ICat) {
            this.mICat = ICat;
        }

        @Override
        public void run() {
            System.out.println("猫抓老鼠");
            mICat.run();
        }
    }

    static class Cat implements ICat{

        @Override
        public void run() {
            System.out.println("猫抓到了老鼠...");
        }
    }
    interface ICat{
        void run();
    }


}
