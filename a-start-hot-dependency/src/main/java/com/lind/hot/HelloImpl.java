package com.lind.hot;

import com.lind.interfaces.Hello;

public class HelloImpl implements Hello {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("HelloImpl finalize");
    }

    @Override
    public String password() {
        return "hello world!";
    }
}
