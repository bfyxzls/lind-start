package com.lind.keycloak.spi;

import org.keycloak.provider.Provider;
import org.keycloak.provider.ProviderFactory;
import org.keycloak.provider.Spi;

public class SelfSpi implements Spi {
    @Override
    public boolean isInternal() {
        return false;
    }

    @Override
    public String getName() {
        return "SelfSpi";
    }

    @Override
    public Class<? extends Provider> getProviderClass() {
        return SelfProvider.class;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Class<? extends ProviderFactory> getProviderFactoryClass() {
        return SelfProviderFactory.class;
    }
}
