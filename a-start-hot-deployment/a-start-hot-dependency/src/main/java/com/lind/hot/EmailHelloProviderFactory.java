package com.lind.hot;

import com.lind.hot.deploy.spi.CarHelloProviderFactory;
import com.lind.spi.ProviderFactory;

public class EmailHelloProviderFactory implements CarHelloProviderFactory<EmailHelloProvider> {

    @Override
    public EmailHelloProvider create() {
        return new EmailHelloProvider();
    }

    @Override
    public String getId() {
        return "EmailHelloProvider";
    }

}
