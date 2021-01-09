package com.lind.hot.deploy.spi;

import com.lind.interfaces.HelloProvider;

public class CarHelloProvider implements HelloProvider {
    @Override
    public String getName() {
        return "CarHelloProvider";
    }
}
