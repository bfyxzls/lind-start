package com.lind.common.proxy;

import com.lind.common.proxy.anno.MessageProvider;
import com.lind.common.proxy.anno.MessageSend;
import com.lind.common.proxy.handler.SmsMessageProviderHandler;

@MessageProvider
public interface PeopleMessageService {
    @MessageSend(messageProviderHandler = SmsMessageProviderHandler.class)
    void send(String message);
}