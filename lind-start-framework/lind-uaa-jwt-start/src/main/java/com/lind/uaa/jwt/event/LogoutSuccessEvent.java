package com.lind.uaa.jwt.event;

import org.springframework.context.ApplicationEvent;

/**
 * 登出的事件.
 */
public class LogoutSuccessEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the com.lind.common.event initially occurred (never {@code null})
     */
    public LogoutSuccessEvent(Object source) {
        super(source);
    }
}

