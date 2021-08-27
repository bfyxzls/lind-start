package com.lind.common.util;

import lombok.Data;
import org.junit.Test;

import java.util.Optional;

public class OptionalTest {
    @Test
    public void nullable() {
        @Data
        class A {
            private String name;
        }
        A a = null;
        System.out.println("result:" + Optional.ofNullable(a)
                .orElse(new A()));
    }
}
