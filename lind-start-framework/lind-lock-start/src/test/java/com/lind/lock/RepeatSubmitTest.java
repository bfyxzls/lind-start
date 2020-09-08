package com.lind.lock;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
@Slf4j
@ActiveProfiles("test")
public class RepeatSubmitTest {
    @Autowired
    RepeatSubmitController repeatSubmitController;

    @Test
    public void test() {
        log.info(repeatSubmitController.get());
    }
}
