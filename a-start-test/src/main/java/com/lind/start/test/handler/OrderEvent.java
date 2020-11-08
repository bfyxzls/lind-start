package com.lind.start.test.handler;

import com.lind.common.handler.ObjectEvent;
import lombok.Getter;
import lombok.Setter;

/**
 * email事件源对象
 */
@Getter
@Setter
public class OrderEvent extends ObjectEvent {
    private static final long serialVersionUID = 1L;
    private String message;
    private String orderId;

    public OrderEvent(String userId, String message) {
        this.setOrderId(userId);
        this.setMessage(message);
    }
}
