package com.lind.hot.deploy.spi;

import com.lind.interfaces.HelloProviderFactory;

public class TestHelloProviderFactory implements HelloProviderFactory<TestHelloProvider> {

    @Override
    public TestHelloProvider create() {
        return new TestHelloProvider();
    }

    @Override
    public String getId() {
        return "TestHelloProvider";
    }

    @Override
    public String getName() {
        return "TestHelloProvider";
    }
}
