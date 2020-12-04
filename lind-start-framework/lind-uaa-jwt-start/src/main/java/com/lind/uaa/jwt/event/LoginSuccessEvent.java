package com.lind.uaa.jwt.event;

import org.springframework.context.ApplicationEvent;

/**
 * 登录成功的事件.
 */
public class LoginSuccessEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public LoginSuccessEvent(Object source) {
        super(source);
    }
}

