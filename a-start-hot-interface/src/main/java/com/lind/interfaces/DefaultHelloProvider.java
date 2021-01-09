package com.lind.interfaces;

public class DefaultHelloProvider implements HelloProvider {
    @Override
    public String getName() {
        return "DefaultHelloProvider";
    }
}
