package com.lind.lock.aspect;

import com.lind.lock.annotation.RepeatSubmit;
import com.lind.lock.exception.RepeatSubmitException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * URL重复提交拦截器.
 */
@Slf4j
@Component
@Aspect
public class RepeatSubmitAspect {
    /**
     * 拦截器执行顺序：
     *
     * @around before
     * @before
     * @around after
     * @after
     */
    @Autowired
    RedisTemplate redisTemplate;

    @Around("@annotation(repeatSubmit)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, RepeatSubmit repeatSubmit) throws Throwable {
        try {
            ServletRequestAttributes attributes =
                    (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            String key = repeatSubmit.redisKey() + ":" + DigestUtils.md5DigestAsHex(request.getServletPath().getBytes());
            // 如果缓存中有这个url视为重复提交
            Object hasSubmit = redisTemplate.opsForValue().get(key);
            if (Objects.isNull(hasSubmit)) {
                redisTemplate.opsForValue().set(key, request.getServletPath());
                redisTemplate.expire(key, repeatSubmit.expireTime(), TimeUnit.SECONDS);
                Object o = proceedingJoinPoint.proceed();
                return o;
            } else {
                String message = String.format("重复提交url:%s", request.getServletPath());
                log.warn(message);
                throw new RepeatSubmitException(message);
            }

        } catch (Throwable e) {
            e.printStackTrace();
            log.error("验证重复提交时出现未知异常!");
            throw e;
        }
    }
}
