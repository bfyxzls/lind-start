package com.lind.common.thread;

import com.lind.common.lock.template.Callback;
import com.lind.common.lock.template.DistributedLockTemplate;
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
    DistributedLockTemplate distributedLockTemplate;

    void lock5Second() {
        distributedLockTemplate.execute("订单流水号", 2, TimeUnit.SECONDS, new Callback() {
            @Override
            public Object onGetLock() throws InterruptedException {
                // 获得锁后要做的事
                log.info("{} 拿到锁，需要5秒钟，这时有请求打入应该被阻塞或者拒绝",Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(5L);
                return null;
            }

            @Override
            public Object onTimeout() throws InterruptedException {
                // 获取到锁（获取锁超时）后要做的事
                log.info("{} 没拿到锁",Thread.currentThread().getName());
                return null;
            }
        });
    }

    @Test
    public void lockTest() throws InterruptedException {
        Thread thread1 = new Thread(() -> lock5Second());
        Thread thread2 = new Thread(() -> lock5Second());
        thread1.start();
        thread2.start();
        TimeUnit.SECONDS.sleep(5L);
    }
}
