package com.lind.interfaces;

public class DefaultHelloProvider implements HelloProvider {
    @Override
    public String login() {
        return "DefaultHelloProvider";
    }
}
