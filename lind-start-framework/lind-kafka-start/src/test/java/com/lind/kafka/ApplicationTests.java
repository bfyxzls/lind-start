package com.lind.kafka;

import com.lind.kafka.entity.MessageEntity;
import com.lind.kafka.handler.FailureHandler;
import com.lind.kafka.receiver.MessageReceiver;
import org.springframework.stereotype.Component;

@Component
public class ApplicationTests implements MessageReceiver<MessageEntity> {

    @Override
    public boolean messageReceive(MessageEntity message, FailureHandler failureHandler) {
        System.out.println(message.getData());
        return true;
    }
}