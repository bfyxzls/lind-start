package com.lind.common;

import com.lind.common.event.AbstractEvent;
import com.lind.common.event.EventBusListener;
import com.lind.common.event.EventBusService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class EventTest {
    @Autowired
    EventBusService eventBusService;

    @Test
    public void doOrder() {
        eventBusService.addEventListener(new SmsListener(), "create");
        eventBusService.addEventListener(event -> System.out.println("email do order"), "create");

        eventBusService.publisher(new OrderEvent(), "create");
    }

    class OrderEvent extends AbstractEvent {
    }

    class SmsListener implements EventBusListener<OrderEvent> {
        @Override
        public void onEvent(OrderEvent event) {
            System.out.println("sms do order");
        }
    }
}
