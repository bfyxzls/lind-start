package com.lind.common.thread;

import com.lind.common.util.JasyptUtils;
import org.junit.Assert;
import org.junit.Test;

public class JasyptUtilTest {
    @Test
    public void encryption() {
        String pwd=JasyptUtils.encyptPwd("xboot", "123456");
        // 加密
        System.out.println(pwd);
        // 解密
        System.out.println(JasyptUtils.decyptPwd("xboot", pwd));
        // 断言
        Assert.assertEquals("123456",JasyptUtils.decyptPwd("xboot", pwd));
    }

    @Test
    public void fail(){
        String b="2";
        Assert.assertEquals("1",b);
    }
}
