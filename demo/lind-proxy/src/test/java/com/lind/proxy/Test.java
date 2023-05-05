package com.lind.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestProviderBeanDefinitionRegistry.class)
public class Test {
    @Autowired
    TestProvider testProvider;

@org.junit.jupiter.api.Test
public void dynamic() {
        testProvider.hello("hello world!");
    }
}
