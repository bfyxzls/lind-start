package com.lind.start.test.handler;

import com.lind.common.handler.ObjectEvent;
import lombok.Getter;
import lombok.Setter;

/**
 * email事件源对象
 */
@Getter
@Setter
public class UserEvent extends ObjectEvent {
    private static final long serialVersionUID = 1L;
    private String message;
    private String userId;

    public UserEvent(String userId, String message) {
        this.setUserId(userId);
        this.setMessage(message);
    }
}
