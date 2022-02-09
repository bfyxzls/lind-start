package com.lind.common.bean.study;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Demo {
    @Autowired
    FactoryBeanService factoryBeanService;

    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(FactoryBeanLearnConfig.class);
        context.getBean(FactoryBeanService.class).testFactoryBean();
    }
}
