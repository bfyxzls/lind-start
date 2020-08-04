package com.lind.start.test;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Component
public class TestService {
    public void print(){
        System.out.print("hello");
    }
}
