package com.lind.common;

import cn.hutool.core.lang.Assert;
import com.lind.common.util.SpringContextUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringContextUtilsTest {
    @Test
    public void getBean() {
        CommonApplication commonApplication = SpringContextUtils.getBean(CommonApplication.class);
        Assert.isFalse(commonApplication == null);
    }
}
