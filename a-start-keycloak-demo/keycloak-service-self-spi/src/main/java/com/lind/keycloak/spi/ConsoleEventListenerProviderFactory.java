package com.lind.keycloak.spi;

import org.keycloak.Config;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

/**
 * @author Pedro Igor
 */
public class ConsoleEventListenerProviderFactory implements EventListenerProviderFactory {
     public static final String PROVIDER_ID = "consoleEvent";

    public ConsoleEventListenerProviderFactory() {
        System.err.println("ConsoleEventListenerProviderFactory.init");
    }

    @Override
    public EventListenerProvider create(KeycloakSession keycloakSession) {
        return new ConsoleEventListenerProvider();
    }

    @Override
    public void init(Config.Scope scope) {

    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {

    }

    @Override
    public void close() {

    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }
}
