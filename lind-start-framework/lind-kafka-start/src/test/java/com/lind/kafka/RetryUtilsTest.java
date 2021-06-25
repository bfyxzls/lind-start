package com.lind.kafka;

import com.lind.kafka.util.RetryUtils;
import org.junit.Test;

public class RetryUtilsTest {
    @Test
    public void test() {
        RetryUtils.reDo(5, () -> {
            int a = 0;
            int b = 100 / a;
        });
    }
}
