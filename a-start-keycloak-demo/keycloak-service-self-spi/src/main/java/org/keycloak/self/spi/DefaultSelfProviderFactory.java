package com.lind.keycloak.spi;

import com.google.auto.service.AutoService;
import lombok.extern.jbosslog.JBossLog;
import org.keycloak.Config;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

@AutoService(SelfProviderFactory.class)
@JBossLog
public class DefaultSelfProviderFactory implements SelfProviderFactory {

    public DefaultSelfProviderFactory() {
        System.err.println("DefaultSelfProviderFactory.init.");
    }

    @Override
    public SelfProvider create(KeycloakSession keycloakSession) {
        return new DefaultSelfProvider();
    }

    @Override
    public void init(Config.Scope scope) {
        log.infov("Configured {0}", scope);
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
