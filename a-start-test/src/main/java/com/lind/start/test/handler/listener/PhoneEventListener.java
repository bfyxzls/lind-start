package com.lind.start.test.handler.listener;

import com.lind.start.test.handler.UserEvent;
import com.lind.start.test.handler.UserEventListener;
import org.springframework.stereotype.Component;

@Component
public class PhoneEventListener implements UserEventListener {
    @Override
    public void onEvent(UserEvent event) {
        System.out.println("phone:" + event.getMessage());
    }
}
