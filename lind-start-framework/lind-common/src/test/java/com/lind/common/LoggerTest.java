package com.lind.common;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class LoggerTest extends AbstractTest {
    @Test
    public void filter() {
        log.info("hello world!");
    }
}
