package com.lind.hot;

import com.lind.interfaces.HelloProvider;

public class EmailHelloProvider implements HelloProvider {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("HelloImpl finalize");
    }

    @Override
    public String getName() {
        return "EmailHelloProvider";
    }
}
