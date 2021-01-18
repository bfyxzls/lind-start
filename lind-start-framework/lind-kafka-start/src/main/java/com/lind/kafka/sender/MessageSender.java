package com.lind.kafka.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lind.kafka.entity.MessageEntity;
import com.lind.kafka.handler.FailureHandler;
import com.lind.kafka.handler.SuccessHandler;

/**
  * @Description TODO
 * @date 2020/7/7 8:31
 **/
public interface MessageSender<T extends MessageEntity> {

    void send(String topic, T message) throws JsonProcessingException;


    void send(String topic, T message, SuccessHandler successHandler) throws JsonProcessingException;

    void send(String topic, T message, FailureHandler failureHandler) throws JsonProcessingException;

    void send(String topic, T message, SuccessHandler successHandler, FailureHandler failureHandler) throws JsonProcessingException;


}
