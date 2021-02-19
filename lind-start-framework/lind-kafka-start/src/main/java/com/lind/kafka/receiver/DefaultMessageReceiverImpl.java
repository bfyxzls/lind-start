package com.lind.kafka.receiver;

import com.lind.kafka.entity.MessageEntity;
import com.lind.kafka.handler.FailureHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 默认订阅者.
 */
@Slf4j
public class DefaultMessageReceiverImpl implements MessageReceiver {

    @Override
    public boolean messageReceive(MessageEntity message, FailureHandler failureHandler) {
        return false;
    }
}
