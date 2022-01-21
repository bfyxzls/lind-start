package com.lind.start.test.aop;

import com.lind.start.test.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AopTest {
    @Autowired
    TestService testService;

    @Test
    public void test() {
        testService.print(new User());
    }
}
