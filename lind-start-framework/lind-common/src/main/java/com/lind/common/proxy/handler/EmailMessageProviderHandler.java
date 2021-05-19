package com.lind.common.proxy.handler;

public class EmailMessageProviderHandler implements MessageProviderHandler {
    @Override
    public void send(String message) {
        System.out.println("email send message:" + message);
    }
}
