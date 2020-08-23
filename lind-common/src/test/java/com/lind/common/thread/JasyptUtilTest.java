package com.lind.common.thread;

import com.lind.common.util.JasyptUtils;
import org.junit.Test;

public class JasyptUtilTest {
    @Test
    public void encryption() {
        //加密
        System.out.println(JasyptUtils.encyptPwd("xboot", "123456"));
        //解密
        System.out.println(JasyptUtils.decyptPwd("xboot", "uOtmALFgsfxgYzEg1uLXl3O/tg6X1Dy25I3SdAeqqETOyZbDwmaW0/EZEusIh6hK"));

    }
}
