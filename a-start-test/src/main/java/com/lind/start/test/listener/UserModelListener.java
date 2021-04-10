package com.lind.common.listener;

import com.lind.common.EventTest;
import com.lind.common.event.EventBusListener;
import org.springframework.stereotype.Component;

// auto register bean.
@Component
public class UserModelListener implements EventBusListener<UserEvent> {
    public void onEvent(UserEvent event) {
        System.out.println("user update.");
    }
}
