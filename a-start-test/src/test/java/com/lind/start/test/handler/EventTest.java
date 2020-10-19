package com.lind.start.test.handler;

import com.lind.common.handler.ObjectEventService;
import com.lind.start.test.handler.listener.EmailEventListener;
import com.lind.start.test.handler.listener.SmsEventListener;
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
    ObjectEventService userEventService;

    @Before
    public void init() {
        userEventService.addEventListener(new SmsEventListener(), UserEventType.LOGIN.name(), UserEventType.DEL.name());
        userEventService.addEventListener(new EmailEventListener(), UserEventType.LOGIN.name());
        userEventService.addEventListener(new EmailEventListener(), UserEventType.LOGIN.name());
    }

    @Test
    public void userRegister() {
        userEventService.publisher(new UserEvent("1", "hello"), UserEventType.LOGIN.name());
    }
}
