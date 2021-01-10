package com.lind.hot.deploy.spi;

import com.lind.spi.Provider;

public class TestHelloProvider implements Provider {
    @Override
    public String login() {
        return "TestHelloProvider";
    }
}
