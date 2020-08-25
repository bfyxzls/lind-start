package com.lind.common;

import com.lind.common.util.ThreadPoolUtils;
import org.junit.Assert;
import org.junit.Test;

public class ThreadPoolUtilsTest {
    @Test
    public void threadSize() {
        Assert.assertEquals(5, ThreadPoolUtils.getPool().getPoolSize());
    }
}
