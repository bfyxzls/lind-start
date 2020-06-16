package com.lind.start.test;

import com.lind.redis.cache.service.RedisService;
import com.lind.start.test.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

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
    public void DateValue() throws ParseException {
        System.out.println(new Date(2020, 0, 1));//注意年和月的问题
        System.out.println(LocalDate.of(2020, 1, 1));//推荐使用
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.parse("2020-6-01"));//推荐使用
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
