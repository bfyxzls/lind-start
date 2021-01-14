package com.lind.common;

import com.lind.common.event.AbstractEvent;
import com.lind.common.event.EventBusListener;
import com.lind.common.event.EventBusService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class EventTest {
    @Autowired
    EventBusService eventBusService;

    @Test
    public void doOrder() {
        eventBusService.addEventListener(new Foo(), "create");
        eventBusService.addEventListener(event -> System.out.println("email do order"), "create");

        eventBusService.publisher(new OrderEvent(), "create");
        eventBusService.publisher(new UserEvent(), "create");

    }

    class OrderEvent extends AbstractEvent {
    }

    class UserEvent extends AbstractEvent {
    }


    @SuppressWarnings("rawtypes")//不用提示使用基本类型参数时相关的警告信息
    public class Foo implements EventBusListener {
        @SuppressWarnings("rawtypes")
        private final Map<Class<? extends AbstractEvent>, EventBusListener> listeners;

        public Foo() {
            @SuppressWarnings("rawtypes")
            Map<Class<? extends AbstractEvent>, EventBusListener> temp = new HashMap<>();
            // LoginEvents will be routed to LoginListener
            temp.put(OrderEvent.class, new LoginListener());
            // LogoutEvents will be routed to LoginListener
            temp.put(UserEvent.class, new LogoutListener());
            listeners = Collections.unmodifiableMap(temp);
        }

        @SuppressWarnings("unchecked") //忽略unchecked警告信息
        @Override
        public void onEvent(AbstractEvent event) {
            if (listeners.containsKey(event.getClass())) {
                listeners.get(event.getClass()).onEvent(event);
            } else {
                /* Screams if a unsupported event gets passed
                 * Comment this line if you want to ignore
                 * unsupported events
                 */
                throw new IllegalArgumentException("Event not supported");
            }
        }

        // Concrete Listener for Login - could be anonymous
        private class LoginListener implements EventBusListener<OrderEvent> {
            public void onEvent(OrderEvent event) {
                System.out.println("Login");
            }
        }

        // Concrete Listener for Logout - could be anonymous
        private class LogoutListener implements EventBusListener<UserEvent> {
            public void onEvent(UserEvent event) {
                System.out.println("Logout");
            }
        }
    }

}
