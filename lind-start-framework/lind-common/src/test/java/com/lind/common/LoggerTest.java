package com.lind.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LoggerTest extends AbstractTest {
    protected final Log log = LogFactory.getLog(this.getClass());

    @Test
    public void filter() {
        log.debug("hello world!");
        log.info("hello world!");
        log.warn("end.");
    }
}
