package com.lind.limit;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * 令牌桶算法限流.
 **/
@Slf4j
public class RedisRaterLimiter {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public String acquireToken(String point, int limit, long timeout) {
        String maxCountKey = "BUCKET:MAX_COUNT:" + point;
        String currCountKey = "BUCKET:CURR_COUNT:" + point;
        // 令牌值
        String token = "T";

        // 无效的限流值 返回token
        if (limit <= 0 || timeout <= 0) {
            return token;
        }
        String valueMaxCount = redisTemplate.opsForValue().get(maxCountKey);
        String currCount = redisTemplate.opsForValue().get(currCountKey);
        // 初始
        if (StringUtils.isBlank(valueMaxCount) && StringUtils.isBlank(currCount)) {
            // 事务
            SessionCallback<Object> sessionCallback = new SessionCallback<Object>() {
                @Override
                public Object execute(RedisOperations redisOperations) throws DataAccessException {
                    redisOperations.multi();
                    // 计数加1
                    redisTemplate.opsForValue().increment(currCountKey);
                    redisTemplate.expire(currCountKey, timeout, TimeUnit.MILLISECONDS);
                    // 总数
                    redisTemplate.opsForValue().set(maxCountKey, String.valueOf(limit), timeout, TimeUnit.MILLISECONDS);
                    return redisOperations.exec();
                }
            };
            redisTemplate.execute(sessionCallback);
            return token;
        } else if (StringUtils.isNotBlank(valueMaxCount) && StringUtils.isNotBlank(currCount)) {
            // 判断是否超过限制
            if (StringUtils.isNotBlank(currCount) && Integer.parseInt(currCount) < Integer.parseInt(valueMaxCount)) {
                // 计数加1
                redisTemplate.opsForValue().increment(currCountKey);
                // 避免key失效 上述语句未设置失效时间
                Long expireTime = redisTemplate.getExpire(currCountKey);
                if (expireTime != null && expireTime == -1L) {
                    redisTemplate.delete(currCountKey);
                }
                return token;
            }
        }
        return null;
    }

}

