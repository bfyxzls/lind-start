package com.lind.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 依赖注入测试.
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class IocTest {
    @Autowired
    People people;

    @Test
    public void setTest() {
        System.out.println(keyCloak.context);
    }
}

@Component
class People {
    /**
     * 注入后，把值赋值其它属性或者方法，不用在外面定义一个ApplicationContext了.
     *
     * @param context
     */
    @Autowired
    public void setApplicationContext(ApplicationContext context) {
        keyCloak.setLindContext(context);
    }

    public void init() {

    }
}

class keyCloak {
    public static ApplicationContext context;

    public static void setLindContext(ApplicationContext context) {
        keyCloak.context = context;
    }
}
