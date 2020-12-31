package com.lind.common;

import com.lind.common.encrypt.HashUtils;
import org.junit.Test;

public class Base163264Test {

    @Test
    public void base64() {
        String toEncoded = HashUtils.encryptBASE64("12345678_f_20200612_D_100_V6".getBytes());
        System.out.println(toEncoded);
        System.out.println(new String(HashUtils.decryptBASE64(toEncoded)));
    }

    @Test
    public void base16() {
        String toEncoded = HashUtils.encryptBASE16("12345678_f_20200612_D_100_V6".getBytes());
        System.out.println(toEncoded);
        System.out.println(new String(HashUtils.decryptBASE16(toEncoded)));

    }
}
