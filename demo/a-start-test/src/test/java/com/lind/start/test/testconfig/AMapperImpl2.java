package com.lind.start.test.testconfig;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AMapperImpl2 implements AMapper {
    private final AConfig config;

    public void print() {
        System.out.print("AMapperImpl2" + config.toString());
    }
}
