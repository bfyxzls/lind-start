package com.lind.logger.aspect;

import com.lind.logger.anno.LogRecord;
import com.lind.logger.entity.LogEvaluationContext;
import com.lind.logger.entity.LogEvaluator;
import com.lind.logger.entity.LogRootObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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

  /**
   * 切点
   */
  @Pointcut("@annotation(com.lind.logger.anno.LogRecord)")
  public void pointcut() {
  }

  @Around("pointcut()")
  public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
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
    Object content = null;
    LogRecord annotation = method.getAnnotation(LogRecord.class);
    if (annotation != null) {
      LogRootObject rootObject = new LogRootObject(method, args, targetClass);
      LogEvaluationContext context = new LogEvaluationContext(rootObject, evaluator.getDiscoverer());
      content = evaluator.parse(annotation.detail(), context);
    }
    return content;
  }
}
