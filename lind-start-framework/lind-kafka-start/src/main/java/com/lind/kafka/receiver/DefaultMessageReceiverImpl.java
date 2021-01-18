package com.lind.kafka.receiver;

import com.lind.kafka.entity.MessageEntity;
import com.lind.kafka.handler.FailureHandler;
import lombok.extern.slf4j.Slf4j;

/**
  * @Description TODO
 * @date 2020/7/7 13:43
 **/
@Slf4j
public class DefaultMessageReceiverImpl implements MessageReceiver {

    @Override
    public boolean messageReceive(MessageEntity message, FailureHandler failureHandler) {

        return false;
    }
}
