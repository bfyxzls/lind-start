package com.lind.common;

import com.lind.common.encrypt.HashUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

@Slf4j
public class StringTest {

    @Test
    public void split(){
        String msg="admin|中国";
        log.info(StringUtils.split(msg,"|")[1]);
    }

    @Test
    public void equals() {
        // 作为对象使用
        String objectString1 = new String("java");
        String objectString2 = new String("java");
        System.out.println(objectString1 == objectString2);         //false
        System.out.println(objectString1.equals(objectString2));    //true

        //作为基本类型使用
        String valueString1 = "java";
        String valueString2 = "java";
        System.out.println(valueString1 == valueString2);         //true
        System.out.println(valueString1.equals(valueString2));    //true

        String concat1 = "hello".concat("world");
        String concat2 = "helloworld";
        System.out.println(concat1 == concat2);         //false
        System.out.println(concat1.equals(concat2));    //true

        String msg1 = "hello world";
        String msg2 = "hello" + " world";
        System.out.println(msg1 == msg2);         //true
        System.out.println(msg1.equals(msg2));    //true

        String substr1 = "hello";
        String substr2 = "hello world".substring(0, 5);
        System.out.println(substr1 == substr2);         //false
        System.out.println(substr1.equals(substr2));    //true
    }

    @Test
    public void substring() {
        String sSublist = "0401";
        String newS = sSublist.substring(0, 2);
        log.info("sSublist:{} newS:{} equal:{}", sSublist, newS, newS == "04");
        log.info("sSublist:{} newS:{} equal:{}", sSublist, newS, newS.equals("04"));

    }


    @Test
    public void md5Test(){
        log.info(HashUtils.md5("1970324841128405bdyh"));
    }
}
