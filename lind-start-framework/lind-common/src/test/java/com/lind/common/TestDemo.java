package com.lind.common;

import com.lind.common.aspect.repeat.TryDo;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TestDemo {
    @TryDo(limit = 10)
    public void print() {
        int a = new Date().getSeconds() % 2;
        System.out.println("a=" + a);
        int c = 1 / a;
    }
}
