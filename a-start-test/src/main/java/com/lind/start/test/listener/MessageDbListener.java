package com.lind.common.listener;

import org.springframework.context.ApplicationListener;

public class MessageDbListener implements ApplicationListener<MessageDataEvent> {
    @Override
    public void onApplicationEvent(MessageDataEvent messageDataEvent) {
        System.out.print(messageDataEvent);

    }
}
