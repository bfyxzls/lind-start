package com.lind.common.proxy.handler;

public class ConsoleMessageProviderHandler implements MessageProviderHandler {
    @Override
    public void send(String message) {
        System.out.println("console send message:" + message);
    }
}
