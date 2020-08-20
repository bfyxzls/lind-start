package com.lind.common.lock.impl;

import com.google.common.collect.ImmutableMap;
import com.lind.common.lock.Callback;
import com.lind.common.lock.config.RedisLockProperty;
import com.lind.common.lock.exception.RedisUserManualLockException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 基于redis分布锁的当前用户手动锁.
 * 功能：根据当前用户去锁定某个资源,之后手动释放,否则超时释放.
 */
@Component
@Slf4j
@ConditionalOnBean(RedisLockTemplate.class)
public class RedisUserManualLockTemplate {
    @Autowired
    RedisLockTemplate redisLockTemplate;
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @Autowired
    RedisLockProperty redisLockProperty;
    @Autowired
    AuditorAware auditorAware;

    /**
     * 执行手动锁.
     *
     * @param lockId
     * @param timeout
     * @param unit
     * @return
     */
    public Object execute(String lockId, Integer timeout, TimeUnit unit) {
        log.info("RedisLockTemplate.time {}", timeout);
        return redisLockTemplate.execute(lockId, timeout, unit, new Callback() {
            @Override
            public Object onGetLock() {
                log.info("获取锁成功");
                if (auditorAware.getCurrentAuditor() == null) {
                    throw new IllegalArgumentException("AuditorAware实例返回的值为空");
                }
                redisTemplate.opsForHash().put(
                        redisLockProperty.getManualLockKey(),
                        lockId,
                        auditorAware.getCurrentAuditor().get());
                return ResponseEntity.ok(ImmutableMap.of("code", "200"));
            }

            @Override
            @SneakyThrows
            public Object onTimeout() {
                log.info("没拿到锁");
                //判断是否为自动锁定的资源
                String currentValue = "";
                if (redisTemplate.opsForHash().hasKey(redisLockProperty.getManualLockKey(), lockId)) {
                    currentValue = redisTemplate.opsForHash().get(redisLockProperty.getManualLockKey(), lockId).toString();
                }

                if (StringUtils.isNotBlank(currentValue)
                        && currentValue.equals(auditorAware.getCurrentAuditor().get())) {
                    return ResponseEntity.ok(ImmutableMap.of("code", "200"));
                }

                throw new RedisUserManualLockException(
                        String.format("记录正被用户%s锁定,将在%s秒后释放",
                        currentValue,
                        redisTemplate.getExpire(redisLockProperty.getRegistryKey() + ":" + lockId)));
            }
        });
    }

    /**
     * 手动取消锁.
     *
     * @param lockId
     */
    public void unLock(String lockId) {
        redisTemplate.opsForHash().delete(redisLockProperty.getManualLockKey(), lockId);
        redisTemplate.expire(redisLockProperty.getRegistryKey() + ":" + lockId, 0, TimeUnit.SECONDS);

    }
}
