package com.lind.start.test.listener;

import com.lind.common.event.EventBusListener;
import com.lind.start.test.handler.UserEvent;
import org.springframework.stereotype.Component;

// auto register bean.
@Component
public class UserModelListener implements EventBusListener<UserEvent> {
    public void onEvent(UserEvent event) {
        System.out.println("user update.");
    }
}
