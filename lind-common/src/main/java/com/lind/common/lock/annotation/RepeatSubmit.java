package com.lind.common.lock.annotation;

import java.lang.annotation.*;

/**
 * 防止重复提交
 *
 * @author BD-PC220
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface RepeatSubmit {
    /**
     * 间隔多长时间提交,默认1秒.
     *
     * @return
     */
    int expireTime() default 1;

    /**
     * redis里存储的重复提交的key.
     *
     * @return
     */
    String redisKey() default "submit-repeat";
}
