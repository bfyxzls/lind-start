package com.lind.keycloak.spi;

import org.keycloak.Config;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

public class DefaultSelfProviderFactory implements SelfProviderFactory {

    public DefaultSelfProviderFactory() {
        System.out.println("DefaultSelfProviderFactory.init.");
    }

    @Override
    public SelfProvider create(KeycloakSession keycloakSession) {
        return new DefaultSelfProvider();
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
        return "defaultSelfProvider";
    }
}
