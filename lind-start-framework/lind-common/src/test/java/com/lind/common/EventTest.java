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
        eventBusService.addEventListener(new LoginListener());
        eventBusService.addEventListener(new LogoutListener());
        eventBusService.addEventListener((EventBusListener<OrderEvent>) event -> System.out.println("email do order"));//目前不支持lambda事件
        eventBusService.publisher(new OrderEvent());

    }



    class OrderEvent extends AbstractEvent {
    }

    // Concrete Listener for Login - could be anonymous
    class LoginListener implements EventBusListener<OrderEvent> {
        public void onEvent(OrderEvent event) {
            System.out.println("Login");
        }
    }

    // Concrete Listener for Logout - could be anonymous
    class LogoutListener implements EventBusListener<OrderEvent> {
        public void onEvent(OrderEvent event) {
            System.out.println("Logout");
        }
    }


}
