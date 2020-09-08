package com.lind.common.listener;

import org.springframework.context.ApplicationEvent;

/**
 * 消息事件.
 */
public class MessageDataEvent extends ApplicationEvent {
    public MessageDataEvent(Object source) {
        super(source);
    }
}
