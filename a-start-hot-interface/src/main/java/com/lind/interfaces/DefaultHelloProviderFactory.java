package com.lind.interfaces;

public class DefaultHelloProviderFactory implements HelloProviderFactory<DefaultHelloProvider> {

    @Override
    public DefaultHelloProvider create() {
        return new DefaultHelloProvider();
    }

    @Override
    public String getId() {
        return "DefaultHelloProvider";
    }

    @Override
    public String getName() {
        return "DefaultHelloProvider";
    }
}
