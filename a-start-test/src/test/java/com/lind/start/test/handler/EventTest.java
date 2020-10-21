package com.lind.start.test.handler;

import com.lind.common.event.EventBusService;
import com.lind.start.test.handler.listener.EmailEventBusListener;
import com.lind.start.test.handler.listener.SmsEventBusListener;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest()
@RunWith(SpringRunner.class)
public class EventTest {
    @Autowired
    EventBusService userEventService;

    @Before
    public void init() {
        userEventService.addEventListener(new SmsEventBusListener(), UserEventType.LOGIN.name(), UserEventType.DEL.name());
        userEventService.addEventListener(new EmailEventBusListener(), UserEventType.LOGIN.name());
        userEventService.addEventListener(new EmailEventBusListener(), UserEventType.LOGIN.name());
    }

    @Test
    public void userRegister() {
        userEventService.publisher(new UserEvent("1", "hello"), UserEventType.LOGIN.name());
    }
}
