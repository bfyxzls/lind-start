package com.lind.common.thread;


import com.lind.common.logger.EsLogger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class LoggerTest {
    @Autowired
    EsLogger logger;

    @Test
    public void insert() {
        logger.info("hello");
    }
}
