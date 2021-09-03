package com.lind.proxy;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(TestProviderBeanDefinitionRegistry.class)
public class Test {
    @Autowired
    TestProvider testProvider;

    @org.junit.Test
    public void dynamic() {
        testProvider.hello("hello world!");
    }
}
