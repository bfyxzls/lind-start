package com.lind.start.test;

import com.lind.common.logger.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest()
@ComponentScan("com.lind.common.logger.ConsoleLogger")
public class CommonTest {
    @Autowired
    Logger logger;

    @Resource(name="defaultLogger")
    Logger defaultLogger;
    @Test
    public void loggerTest() {
        logger.info("logger在spring.factories里进行配置");
        defaultLogger.info("logger在spring.factories里进行配置");
    }
}
