package com.lind.hot;

import com.lind.interfaces.HelloProviderFactory;

public class EmailHelloProviderFactory implements HelloProviderFactory<EmailHelloProvider> {

    @Override
    public EmailHelloProvider create() {
        return new EmailHelloProvider();
    }

    @Override
    public String getId() {
        return "EmailHelloProvider";
    }

    @Override
    public String getName() {
        return "EmailHelloProvider";
    }
}
