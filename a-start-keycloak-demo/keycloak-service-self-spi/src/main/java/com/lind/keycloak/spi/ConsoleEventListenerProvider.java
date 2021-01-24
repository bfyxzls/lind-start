package com.lind.keycloak.spi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;

/**
 * @author <a href="mailto:sthorger@redhat.com">Stian Thorgersen</a>
 */
public class ConsoleEventListenerProvider implements EventListenerProvider {
    private String toString(Event event) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(event);
    }

    public ConsoleEventListenerProvider() {
        System.err.println("ConsoleEventListenerProvider.init");

    }

    private String toAdminString(AdminEvent event) throws JsonProcessingException {
         return new ObjectMapper().writeValueAsString(event);
    }

    @Override
    public void onEvent(Event event) {
        try {
            System.out.println("Event Occurred:" + toString(event));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

     @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {
         try {
             System.out.println("Admin Event Occurred:" + toAdminString(adminEvent));
         } catch (JsonProcessingException e) {
             e.printStackTrace();
         }
     }

    @Override
    public void close() {

    }
}
