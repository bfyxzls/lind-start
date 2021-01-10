package com.lind.hot.deploy.spi;

import com.lind.spi.Provider;

public class CarHelloProvider implements Provider {
    @Override
    public String login() {
        return "CarHelloProvider";
    }
}
