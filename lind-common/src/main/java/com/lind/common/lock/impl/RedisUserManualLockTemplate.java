package com.lind.common.lock.impl;

import com.google.common.collect.ImmutableMap;
import com.lind.common.lock.Callback;
import com.lind.common.lock.config.RedisLockProperty;
import com.lind.common.lock.exception.RepeatSubmitException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 基于redis实现的当前用户手动锁.
 * 功能：根据当前用户去锁定某个资源,之后手动释放,否则超时释放.
 */
@Component
@Slf4j
@ConditionalOnBean(RedisLockTemplate.class)
public class RedisUserManualLockTemplate {
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @Autowired
    RedisLockProperty redisLockProperty;
    @Autowired
    AuditorAware<String> auditorAware;
    @Autowired
    RedisLockTemplate redisLockTemplate;

    /**
     * 执行手动锁.
     *
     * @param sourceId
     * @param timeout
     * @param unit
     * @return
     */
    public Object execute(String sourceId, Integer timeout, TimeUnit unit) {
        Assert.notNull(sourceId, "sourceId不能为空");
        String key = getKey(sourceId);
        String user = auditorAware.getCurrentAuditor().orElse(null);
        Assert.notNull(user, "AuditorAware实例返回的值为空");
        return redisLockTemplate.execute(sourceId, timeout, unit, new Callback() {
            @Override
            public Object onGetLock() {
                onGetLockValidate(key, user);
                redisTemplate.opsForValue().set(
                        key,
                        user,
                        timeout,
                        unit);
                log.info("获取锁成功，需要检查锁定列表里是否是自己的资源.");
                return ResponseEntity.ok(ImmutableMap.of("code", "200"));
            }

            @Override
            public Object onTimeout() {
                log.info("没拿到锁，需要判断自己是否已经拿到了资源.");
                return onTimeoutValidate(key, user);
            }
        });
    }

    /**
     * 当获取锁成功之后,判断是否为自己的资源,如果不是自己的则失败.
     *
     * @param key
     * @param user
     */
    void onGetLockValidate(String key, String user) {
        if (redisTemplate.hasKey(key)) {
            String currentValue = redisTemplate.opsForValue().get(key);
            if (StringUtils.isBlank(currentValue) || !currentValue.equals(user)) {
                throw new RepeatSubmitException(
                        String.format("记录正被用户%s锁定,将在%s秒后释放",
                                currentValue,
                                redisTemplate.getExpire(key)));
            }
        }
    }

    /**
     * 当获取锁失败之后,判断这个资源是否在自己的锁里,如果是则成功.
     *
     * @param key
     * @param user
     * @return
     */
    Object onTimeoutValidate(String key, String user) {
        String currentValue = "";
        if (redisTemplate.hasKey(key)) {
            currentValue = redisTemplate.opsForValue().get(key);
            if (StringUtils.isNotBlank(currentValue) && currentValue.equals(user)) {
                return ResponseEntity.ok(ImmutableMap.of("code", "200"));
            }
        }
        throw new RepeatSubmitException(
                String.format("记录正被用户%s锁定,将在%s秒后释放",
                        currentValue,
                        redisTemplate.getExpire(key)));
    }

    /**
     * 得到key.
     *
     * @param lockId
     * @return
     */
    String getKey(@NotNull String lockId) {
        String key = redisLockProperty.getManualLockKey() + ":" + lockId;
        return key;
    }

    /**
     * 手动取消锁.
     *
     * @param lockId
     */
    public void unLock(String lockId) {
        redisTemplate.expire(getKey(lockId), 0, TimeUnit.SECONDS);
    }

    /**
     * 得是被锁定的资源列表.
     *
     * @return
     */
    public Set<Map<String, String>> getSourceList() {
        Set<String> keys = redisTemplate.keys(redisLockProperty.getManualLockKey() + "*");
        Set<Map<String, String>> maps = new HashSet<>();
        for (String key : keys) {
            maps.add(ImmutableMap.of("sourceId", key, "userId", redisTemplate.opsForValue().get(key)));
        }
        return maps;
    }

    /**
     * 查看锁定资源的用户.
     *
     * @return
     */
    public String getUserBySourceId(String sourceId) {
        return redisTemplate.opsForValue().get(getKey(sourceId));
    }
}
