package com.lind.common.lock.impl;

import com.lind.common.lock.Callback;
import com.lind.common.lock.DistributedLockTemplate;
import com.lind.common.lock.config.RedisLockProperty;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@Slf4j
@Component
@ConditionalOnProperty(value = "redis.lock.enable", havingValue = "true", matchIfMissing = true)
public class RedisLockTemplate implements DistributedLockTemplate {

    @Autowired
    private RedisLockRegistry redisLockRegistry;
    @Autowired
    private RedisLockProperty redisLockProperty;

    @SneakyThrows //向上层抛出异常
    @Override
    public Object execute(String lockId, Integer timeout, TimeUnit unit, Callback callback) {
        Lock lock = null;
        boolean getLock = false;
        try {
            lock = redisLockRegistry.obtain(lockId);
            if (redisLockProperty.getInterrupt()) {
                getLock = lock.tryLock();//中断执行,立即返回
            } else {
                getLock = lock.tryLock(timeout, unit); //阻塞执行,实现可重入锁，每100ms重试一次
            }
            if (getLock) {
                // 拿到锁
                return callback.onGetLock();
            } else {
                // 未拿到锁，它会进行阻塞
                return callback.onTimeout();
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            log.error(ex.getMessage(), ex);
            throw ex;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        } finally {
            if (getLock) {
                // 释放锁
                lock.unlock();
            }
        }
    }
}
