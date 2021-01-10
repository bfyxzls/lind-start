package com.lind.hot.deploy.spi;

import com.lind.spi.ProviderFactory;

public class CarHelloProviderFactory implements ProviderFactory<CarHelloProvider> {

    @Override
    public CarHelloProvider create() {
        return new CarHelloProvider();
    }

    @Override
    public String getId() {
        return "CarHelloProvider";
    }
}
