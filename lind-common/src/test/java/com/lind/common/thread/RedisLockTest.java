package com.lind.common.thread;

import com.lind.common.lock.Callback;
import com.lind.common.lock.RedisLockTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest()
@Slf4j
@ActiveProfiles("test")
public class RedisLockTest {

    @Autowired
    RedisLockTemplate redisLockTemplate;

    @Test
    public void lockTest() {
        redisLockTemplate.execute("订单流水号", 2, TimeUnit.SECONDS, new Callback() {
            @Override
            public Object onGetLock() throws InterruptedException {
                // 获得锁后要做的事
                log.info("生成订单流水号，需要5秒钟，些时有请求打入应该被阻塞或者拒绝");
                Thread.sleep(5000);
                return null;
            }

            @Override
            public Object onTimeout() throws InterruptedException {
                // 获取到锁（获取锁超时）后要做的事
                log.info("没拿到锁");
                return null;
            }
        });
    }
}
