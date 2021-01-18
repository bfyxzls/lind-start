package com.lind.kafka.aop;


import com.lind.kafka.entity.CurrentUserAware;
import com.lind.kafka.entity.MessageEntity;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description TODO
 * @date 2020/7/7 11:37
 **/
@Aspect
@Component
@RequiredArgsConstructor
public class KafkaSenderInspector {

    @Autowired
    CurrentUserAware currentUserAware;

    @Pointcut("execution(* com.lind.kafka.sender.MessageSender.send(..))")
    public void pointcut() {

    }


    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();

        if (args.length > 1) {
            Object arg = args[1];
            if (arg instanceof MessageEntity) {
                //填充发送时间
                if (((MessageEntity) arg).getSendTime() == null) {
                    ((MessageEntity) arg).setSendTime(new Date());
                }


                //填充发送消息的人
                String currentUserName = currentUserAware.getCurrentUserName();
                ((MessageEntity) arg).setSendUser(currentUserName);

            }
        }
        return pjp.proceed();
    }

}
