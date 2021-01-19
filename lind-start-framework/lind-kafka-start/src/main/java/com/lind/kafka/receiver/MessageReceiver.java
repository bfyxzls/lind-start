package com.lind.kafka.receiver;


import com.lind.kafka.entity.MessageEntity;
import com.lind.kafka.handler.FailureHandler;

/**
 * @author BD-PC220
 */
public interface MessageReceiver<T extends MessageEntity> {


    /**
     * 接收消息
     *
     * @param message
     * @param failureHandler
     * @return
     */
    boolean messageReceive(T message, FailureHandler failureHandler);


}