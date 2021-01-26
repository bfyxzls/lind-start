package com.lind.keycloak.spi;

import com.google.auto.service.AutoService;

@AutoService(SelfProvider.class)
public class DefaultSelfProvider implements  SelfProvider {
    @Override
    public void close() {

    }
}
