package com.lind.kafka.handler;

import com.lind.kafka.entity.MessageEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * 消息发送失败默认事件.
 */
@Slf4j
public class DefaultFailureHandler implements FailureHandler {


    @Override
    public void onFailure(String topic, MessageEntity messageEntity, Throwable ex) {
        log.error("fail to send topic={}, data={}", topic, messageEntity, ex);
    }
}
