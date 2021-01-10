package com.lind.hot.deploy.spi;

import com.lind.spi.ProviderFactory;

public class TestHelloProviderFactory implements ProviderFactory<TestHelloProvider> {

    @Override
    public TestHelloProvider create() {
        return new TestHelloProvider();
    }

    @Override
    public String getId() {
        return "TestHelloProvider";
    }

}
