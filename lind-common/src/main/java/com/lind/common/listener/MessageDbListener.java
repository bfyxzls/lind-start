package com.lind.common.listener;

import org.springframework.context.ApplicationListener;

public class MessageDbListener implements ApplicationListener<MessageDataEvent> {
    @Override
    public void onApplicationEvent(MessageDataEvent messageData) {
        System.out.print(messageData);
    }
}
