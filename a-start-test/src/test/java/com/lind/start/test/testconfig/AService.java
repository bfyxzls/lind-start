package com.lind.start.test.testconfig;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AService {
    private final AMapper mapper;
    private final String name;

    public void print() {
        mapper.print();
    }
}
