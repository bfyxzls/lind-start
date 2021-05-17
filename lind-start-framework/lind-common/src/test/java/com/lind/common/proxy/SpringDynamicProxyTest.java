package com.lind.common.proxy;

import com.lind.common.proxy.anno.EnableServiceProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * spring动态代理bean,用于Spring动态注入自定义接口到ioc里.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableServiceProvider
public class SpringDynamicProxyTest {
    @Autowired
    DemoService calculateService;

    @Test
    public void testProxy() {
        Peo peo = new Peo();
        peo.setName("lind");
        calculateService.send(new MessageEntity<>());
    }



    public class Peo extends MessageEntity {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
