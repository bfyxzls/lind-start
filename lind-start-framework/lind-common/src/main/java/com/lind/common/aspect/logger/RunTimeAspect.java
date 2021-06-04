package com.lind.common.aspect.logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lind.common.util.StopWatchUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Aspect
@Slf4j
@Order(1)
@RequiredArgsConstructor
public class RunTimeAspect {


    private final ObjectMapper objectMapper;

    /**
     * 目前只做简单的时间打印
     *
     * @param point
     * @param runTime
     * @return
     * @throws Throwable
     */
    @Around("@annotation(runTime)")
    public Object around(ProceedingJoinPoint point, RunTime runTime) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(point.getSignature().getDeclaringType().getTypeName())
                .append(".")
                .append(point.getSignature().getName())
                .append("()");
        String method = stringBuilder.toString();
        return StopWatchUtils.returnTimer(method, () -> {
            Object proceed = null;
            try {
                proceed = point.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return proceed;
        });
    }


}
