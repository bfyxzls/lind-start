package com.lind.logger.aspect;

import com.lind.logger.anno.LogRecord;
import com.lind.logger.entity.LogEvaluationContext;
import com.lind.logger.entity.LogEvaluator;
import com.lind.logger.entity.LogRootObject;
import com.lind.logger.entity.LoggerInfo;
import com.lind.logger.service.CurrentIpAware;
import com.lind.logger.service.CurrentUserAware;
import com.lind.logger.service.LoggerService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Aspect
public class LogRecordAspect {
    /**
     * 日志SpEL解析器
     */
    private final LogEvaluator evaluator = new LogEvaluator();
    @Autowired
    CurrentUserAware currentUserAware;
    @Autowired
    LoggerService loggerService;
    @Autowired
    CurrentIpAware currentIpAware;

    /**
     * 切点
     */
    @Pointcut("@annotation(com.lind.logger.anno.LogRecord)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            Object obj = proceedingJoinPoint.proceed();
            loggerService.insert(generateLog(proceedingJoinPoint));
            return obj;

        } finally {
        }
    }

    /**
     * 生成日志实体
     *
     * @param joinPoint 切入点
     * @return 日志实体
     */
    private LoggerInfo generateLog(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        Object[] args = joinPoint.getArgs();
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(joinPoint.getTarget());
        LogRecord annotation = method.getAnnotation(LogRecord.class);
        if (annotation != null) {
            LogRootObject rootObject = new LogRootObject(method, args, targetClass);
            LogEvaluationContext context = new LogEvaluationContext(rootObject, evaluator.getDiscoverer());
            Object content = evaluator.parse(annotation.detail(), context);
            LoggerInfo loggerInfo = LoggerInfo.builder()
                    .detail(content.toString())
                    .moduleType(annotation.moduleType())
                    .dataId(Optional.ofNullable(evaluator.parse(annotation.dataId(), context)).orElse("").toString())
                    .dataTitle(Optional.ofNullable(evaluator.parse(annotation.dataTitle(), context)).orElse("").toString())
                    .operateType(annotation.operateType())
                    .operateTime(LocalDateTime.now())
                    .operatorIp(currentIpAware.address())
                    .operator(currentUserAware.username()).build();
            return loggerInfo;
        }
        return null;
    }
}
