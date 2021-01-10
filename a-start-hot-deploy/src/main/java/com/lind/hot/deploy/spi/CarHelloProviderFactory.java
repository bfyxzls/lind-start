package com.lind.hot.deploy.spi;

import com.lind.interfaces.HelloProviderFactory;

public class CarHelloProviderFactory implements HelloProviderFactory<CarHelloProvider> {

    @Override
    public CarHelloProvider create() {
        return new CarHelloProvider();
    }

    @Override
    public String getId() {
        return "CarHelloProvider";
    }

    @Override
    public String getName() {
        return "CarHelloProvider";
    }
}
