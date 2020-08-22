package com.lind.common.thread;

import com.lind.common.listener.MessageDataEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class ListenerTest {
    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Test
    public void publishEvent() {
       applicationEventPublisher.publishEvent(new MessageDataEvent("hello world!"));
    }
}
