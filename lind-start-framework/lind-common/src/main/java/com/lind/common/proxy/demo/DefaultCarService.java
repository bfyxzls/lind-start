package com.lind.common.proxy.demo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultCarService implements CarService {


    @Override
    public void doing(String name, CarHandler carHandler) {
        System.out.println("default car service.");
        carHandler.OnAfter();
    }
}
