package com.lind.common;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

public class RegexTest {
    private static final Logger logger= LoggerFactory.getLogger(RegexTest.class);
    private static final Pattern DOT = Pattern.compile("\\.");
    @Test
    public void authCode(){
        String[] parsed =DOT.split("abc.123.400.aa",3);//限制3位，这个数组被分为abc,123,400.aa，最后一位.就不进行拆分了
        logger.info("{}",parsed);
    }
}
