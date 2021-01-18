package com.lind.kafka.handler;

import com.lind.kafka.entity.MessageEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description TODO
 * @date 2020/7/7 11:36
 **/
@Slf4j
public class DefaultFailureHandler implements FailureHandler {


    @Override
    public void onFailure(String topic, MessageEntity messageEntity, Throwable ex) {
        log.error("unable to send topic={}, data={}", topic, messageEntity, ex);
    }
}
