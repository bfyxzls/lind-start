package com.lind.mybatis.audit;

import com.lind.mybatis.config.SpringContextConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启Mybatis审计功能.
 * 主要对建立人、建立时间、更新人、更新时间赋值.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({AuditInterceptor.class, SpringContextConfig.class})
public @interface EnableMybatisAuditing {
}