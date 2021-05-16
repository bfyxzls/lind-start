package com.lind.common.proxy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * spring动态代理bean,用于Spring动态注入自定义接口到ioc里.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringDynamicProxyTest {
    @Qualifier(value = "calculateServiceImpl")
    @Autowired
    CalculateService calculateService;
    @Autowired
    TestService testService;

    @Test
    public void testProxy() {
        calculateService.getResult("lind");
        calculateService.insert("ok");

    }

    public interface CalculateService {

        String getResult(String name);

        void insert(String entity);
    }


    public interface TestService {

        String getList(String code, String name);
    }
}
