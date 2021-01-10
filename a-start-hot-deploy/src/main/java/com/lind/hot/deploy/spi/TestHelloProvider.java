package com.lind.hot.deploy.spi;

import com.lind.interfaces.HelloProvider;

public class TestHelloProvider implements HelloProvider {
    @Override
    public String login() {
        return "TestHelloProvider";
    }
}
