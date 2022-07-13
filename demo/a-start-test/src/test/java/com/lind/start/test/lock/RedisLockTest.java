package com.lind.start.test.lock;

import com.lind.redis.lock.config.RedisLockProperty;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
@Slf4j
public class RedisLockTest {
    @Autowired
    RedisLockProperty redisLockProperty;

    @Test
    public void selfProperty() {
        log.info("redis config:{}", redisLockProperty.toString());
    }
}
