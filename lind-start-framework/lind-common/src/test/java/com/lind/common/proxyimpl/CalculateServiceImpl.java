package com.lind.common.proxyimpl;

import com.lind.common.proxy.SpringDynamicProxyTest;
import org.springframework.stereotype.Component;

@Component
public class CalculateServiceImpl implements SpringDynamicProxyTest.CalculateService {

    @Override
    public String getResult(String name) {
        return "ok";
    }

    @Override
    public void insert(String entity) {
        System.out.println("insert entity");
    }
}