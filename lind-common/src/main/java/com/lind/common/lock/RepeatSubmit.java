package com.lind.common.lock;

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
     * 间隔多长时间提交，默认1秒
     *
     * @return
     */
    long time() default 1;

    /**
     * 作为验证重复提交的key,
     *
     * @return
     */
    String key();
}
