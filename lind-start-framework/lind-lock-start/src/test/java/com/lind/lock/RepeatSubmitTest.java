package com.lind.lock;

import com.lind.lock.config.RedisLockConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
@Slf4j
@ContextConfiguration(classes = {RedisLockConfig.class, RepeatSubmitController.class})
public class RepeatSubmitTest {
    @Autowired
    RepeatSubmitController repeatSubmitController;

    @Test
    public void test() {
        for (int i = 1; i < 10; i++)
            log.info(repeatSubmitController.get());
    }
}
