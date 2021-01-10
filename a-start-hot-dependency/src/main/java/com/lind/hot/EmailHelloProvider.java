package com.lind.hot;

import com.lind.interfaces.HelloProvider;

public class EmailHelloProvider implements HelloProvider {

    @Override
    public String login() {
        return "EmailHelloProvider";
    }
}
