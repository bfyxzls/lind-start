package com.lind.kafka.handler;

import com.lind.kafka.entity.MessageEntity;

/**
 * 发送消息失败回调
 *
 * @author BD-PC220
 */
@FunctionalInterface
public interface FailureHandler {


    /**
     * 失败回调函数
     *
     * @param topic
     * @param messageEntity
     * @param ex
     */
    void onFailure(String topic, MessageEntity messageEntity, Throwable ex);
}