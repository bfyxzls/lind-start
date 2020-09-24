package com.lind.start.test.redis;

import com.lind.redis.cache.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
@Slf4j
public class ClusterTest {
    @Autowired
    RedisService redisService;

    @Test
    public void stringSet() {
        redisService.set("test", "测试");
    }
}
