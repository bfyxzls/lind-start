package com.lind.common.proxy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lind.common.proxy.handler.EventHandler;

public interface MessageSender<T extends MessageEntity> {
    void send(String topic, T message) throws JsonProcessingException;
    void send(String topic, T message, EventHandler eventHandler) throws JsonProcessingException;
}