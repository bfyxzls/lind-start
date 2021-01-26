package com.lind.keycloak.spi.system;

import com.lind.keycloak.spi.DefaultSelfProvider;
import org.keycloak.Config;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.services.resource.RealmResourceProvider;
import org.keycloak.services.resource.RealmResourceProviderFactory;

public class HelloWorldProviderFactory implements RealmResourceProviderFactory {

    public static final String ID = "hello";

    public HelloWorldProviderFactory() {
        System.err.println("HelloWorldProviderFactory.init");
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public int order() {
        return 0;
    }

    @Override
    public RealmResourceProvider create(KeycloakSession keycloakSession) {
        DefaultSelfProvider defaultSelfProvider = keycloakSession.getProvider(DefaultSelfProvider.class);
        System.err.println("defaultSelfProvider=" + defaultSelfProvider);
        return new HelloWorldProvider(keycloakSession);
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
}