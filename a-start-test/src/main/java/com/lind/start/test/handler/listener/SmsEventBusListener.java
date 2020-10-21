package com.lind.start.test.handler.listener;

import com.lind.start.test.handler.UserEvent;
import com.lind.start.test.handler.UserEventBusListener;
import org.springframework.stereotype.Component;

@Component
public class SmsEventBusListener implements UserEventBusListener {


    @Override
    public void onEvent(UserEvent event) {
        System.out.println("sms:" + event.toString());
    }
}
