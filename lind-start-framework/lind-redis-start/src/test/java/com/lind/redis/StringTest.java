package com.lind.redis;

import com.lind.redis.config.LettuceRedisAutoConfigure;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Slf4j
@TestPropertySource("classpath:application.yml")  //配置文件注入
@SpringBootTest(classes = {LettuceConnectionFactory.class, LettuceRedisAutoConfigure.class})
public class StringTest {
    @Autowired
    RedisTemplate<String,String> redisService;

    @Test
    public void write() {
        redisService.opsForValue().set("test", "oktest");
    }
}
