package com.lind.start.test.aop;


import com.lind.start.test.model.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TestAspect {

    /**
     * 对TestService类下面的所有方法拦截.
     */
    @Pointcut("execution(* com.lind.start.test.aop.TestService.*(..))")
    public void pointcut() {
    }

    //前置通知
    @Before("pointcut()")
    public void beforeMethod(JoinPoint joinPoint) {
        if (joinPoint.getArgs().length == 1 && joinPoint.getArgs()[0] instanceof User) {
            User user = (User) joinPoint.getArgs()[0];
            user.setUsername("aop赋值");
            log.info("调用了前置通知" + user.toString());
        }

    }

    //@After: 后置通知
    @After("pointcut()")
    public void afterMethod(JoinPoint joinPoint) {
        log.info("调用了后置通知");
    }

    //@AfterRunning: 返回通知 result为返回内容
    @AfterReturning(value = "pointcut()", returning = "result")
    public void afterReturningMethod(JoinPoint joinPoint, Object result) {
        log.info("调用了返回通知");
    }


    //@Around：环绕通知
    @Around("pointcut()")
    public Object Around(ProceedingJoinPoint pjp) throws Throwable {
        log.info("around执行方法之前");
        Object object = pjp.proceed();
        log.info("around执行方法之后--返回值：" + object);
        return object;
    }

}