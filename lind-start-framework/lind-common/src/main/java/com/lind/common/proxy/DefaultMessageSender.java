package com.lind.common.proxy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lind.common.proxy.handler.EventHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultMessageSender<T extends MessageEntity> implements MessageSender {

    @Override
    public void send(String topic, MessageEntity message) throws JsonProcessingException {
        log.info("topic:{},message:{}", topic, message);
    }

    @Override
    public void send(String topic, MessageEntity message, EventHandler eventHandler) throws JsonProcessingException {
        try {
            log.info("topic:{},message:{}", topic, message);
            eventHandler.onSuccess(topic, message);

        } catch (RuntimeException ex) {
            eventHandler.onFailure(topic, message, ex);
        }
    }
}