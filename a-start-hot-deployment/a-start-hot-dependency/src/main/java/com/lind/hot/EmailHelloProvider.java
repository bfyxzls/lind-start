package com.lind.hot;

import com.lind.interfaces.PrinterProvider;

public class EmailHelloProvider extends PrinterProvider {


    @Override
    public String login() {
        return "EmailHelloProvider";
    }
}
