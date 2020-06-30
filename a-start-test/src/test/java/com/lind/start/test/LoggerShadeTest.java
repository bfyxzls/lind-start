package com.lind.start.test;

import com.google.common.base.Optional;
import org.junit.Test;

/**
 * shade插件测试.
 */
public class LoggerShadeTest {
    @Test
    public void hello() {
        Integer invalidInput = 1;
        Optional<Integer> a = Optional.of(invalidInput);
        Optional<Integer> b = Optional.of(new Integer(10));
        System.out.println(sum(a, b));

    }

    public Integer sum(Optional<Integer> a, Optional<Integer> b) {
        return a.get() + b.get();
    }

}
