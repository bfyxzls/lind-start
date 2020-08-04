package com.lind.elasticsearch.entity;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AuditAspect {
    /**
     * 开会之前--找个位置坐下
     */
    @Pointcut("execution(* org.springframework.data.elasticsearch.repository.support..*.save(..))")
    public void save() {}

    @Before("save()")
    public void before() {
        System.out.println("保存之前");
    }
}
