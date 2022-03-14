package com.lind.logger.aspect;

import com.lind.logger.anno.LogRecordAnnotation;
import com.lind.logger.entity.LogEvaluationContext;
import com.lind.logger.entity.LogEvaluator;
import com.lind.logger.entity.LogRootObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.framework.AopProxyUtils;

import java.lang.reflect.Method;

@Slf4j
@Aspect
public class LogRecordAspect {

  /**
   * 日志SpEL解析器
   */
  private final LogEvaluator evaluator = new LogEvaluator();

  @Around("@annotation(logRecordAnnotation)")
  public Object around(ProceedingJoinPoint proceedingJoinPoint, LogRecordAnnotation logRecordAnnotation) throws Throwable {
    try {
      log.info("{}", generateLog(proceedingJoinPoint));
      return proceedingJoinPoint.proceed();
    } finally {
    }
  }

  /**
   * 生成日志实体
   *
   * @param joinPoint 切入点
   * @return 日志实体
   */
  private Object generateLog(ProceedingJoinPoint joinPoint) {
    Signature signature = joinPoint.getSignature();
    MethodSignature methodSignature = (MethodSignature) signature;
    Method method = methodSignature.getMethod();
    Object[] args = joinPoint.getArgs();
    Class<?> targetClass = AopProxyUtils.ultimateTargetClass(joinPoint.getTarget());
    LogRecordAnnotation annotation = method.getAnnotation(LogRecordAnnotation.class);
    LogRootObject rootObject = new LogRootObject(method, args, targetClass);
    LogEvaluationContext context = new LogEvaluationContext(rootObject, evaluator.getDiscoverer());
    Object content = evaluator.parse(annotation.detail(), context);
    return content;
  }
}
