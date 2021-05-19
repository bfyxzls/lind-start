package com.lind.common.proxy.handler;

public class SmsMessageProviderHandler implements MessageProviderHandler {
    @Override
    public void send(String message) {
        System.out.println("sms send message:" + message);
    }
}
