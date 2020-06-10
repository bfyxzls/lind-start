package com.lind.start.test;

import com.lind.redis.cache.service.RedisService;
import com.lind.start.test.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class RedisCacheTest {
    @Autowired
    RedisService redisService;

    @Test
    public void stringValue() {
        redisService.set("hello-test-redis.cache", "lind");
        Assert.assertEquals("lind", redisService.get("hello-test-redis.cache"));
    }

    @Test
    public void objectValue() {
        User user = new User();
        user.setId("1");
        user.setUsername("lind");
        redisService.set("hello-test-redis.cache.object", user);
        User redisObj = (User) redisService.get("hello-test-redis.cache.object");
        Assert.assertEquals("lind", redisObj.getUsername());
    }
}
