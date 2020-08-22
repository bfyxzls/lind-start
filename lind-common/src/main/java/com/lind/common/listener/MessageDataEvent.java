package com.lind.common.listener;

import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ApplicationContextEvent;

import java.util.Date;

public class MessageDataEvent extends ApplicationEvent {
    public MessageDataEvent(Object source) {
        super(source);
    }
}
