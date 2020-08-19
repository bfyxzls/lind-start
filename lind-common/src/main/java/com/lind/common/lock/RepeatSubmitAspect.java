package com.lind.common.lock;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.UUID;

/**
 * URL重复提交拦截器.
 */
@Slf4j
@Component
@Aspect
public class RepeatSubmitAspect {
    /**
     * 拦截器执行顺序：
     * @around before
     * @before
     * @around after
     * @after
     */
    @Autowired
    RedisTemplate redisTemplate;

    @Before("@annotation(repeatSubmit)")
    public void before(JoinPoint joinPoint, RepeatSubmit repeatSubmit) throws Throwable {
        log.info("@before repeatSubmit={}", repeatSubmit.toString());
    }

    @After("@annotation(repeatSubmit)")
    public void after(JoinPoint joinPoint, RepeatSubmit repeatSubmit) throws Throwable {
        log.info("@after repeatSubmit={}", repeatSubmit.toString());
    }

    @Around("@annotation(repeatSubmit)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, RepeatSubmit repeatSubmit) throws Throwable {
        log.info("@around before repeatSubmit={}", repeatSubmit.toString());
        try {
            ServletRequestAttributes attributes =
                    (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            String key = repeatSubmit.key();

            if (StringUtils.isBlank(key)) {
                key = UUID.randomUUID().toString();
            }

            // 如果缓存中有这个url视为重复提交
            Object hasSubmit = redisTemplate.opsForHash().get("submit", key);
            if (Objects.isNull(hasSubmit)) {
                Object o = proceedingJoinPoint.proceed();
                log.info("@around after repeatSubmit={}", repeatSubmit.toString());
                redisTemplate.opsForHash().put("submit", key, repeatSubmit.time());
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
