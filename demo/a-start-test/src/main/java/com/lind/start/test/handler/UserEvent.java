package com.lind.start.test.handler;

import com.lind.common.event.AbstractEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * email事件源对象
 */
@Getter
@Setter
@ToString(callSuper = true)
public class UserEvent extends AbstractEvent {
    private String message;
    private String userId;

    public UserEvent(String userId, String message) {
        this.setUserId(userId);
        this.setMessage(message);
    }
}
