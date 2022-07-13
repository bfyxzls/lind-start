package com.lind.redis;

import com.lind.redis.config.LettuceRedisAutoConfigure;
import com.lind.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest(classes = {LettuceConnectionFactory.class, LettuceRedisAutoConfigure.class})
public class StringTest {
    @Autowired
    RedisService redisService;

    @Test
    public void write() {
        redisService.set("test", "oktest");
    }
}
