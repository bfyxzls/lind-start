package com.lind.start.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AopTest {
    @Autowired
    TestService testService;

    @Test
    public void test() {
        testService.print();
    }
}
