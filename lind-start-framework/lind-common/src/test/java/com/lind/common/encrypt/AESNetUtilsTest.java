package com.lind.common.encrypt;

import com.lind.common.zip.LZString;
import org.junit.Test;

public class AESNetUtilsTest {
    @Test
    public void aes_base16_lz() {
        String str = "12345678_f_20200612_D_100_V6";
        String key = "225E8C70688FD19C5C01A212302322ED";
        String aes = AESNetUtils.encrypt(str, key);
        String result = LZString.compress(aes);
        System.out.println("code=" + result);
        System.out.println("rle=" + LZString.decompress(result));
    }

    @Test
    public void test2() {
        String code = AESNetUtils.encrypt("123456", "keyphrasekeyphra");
        System.out.println("base64:" + code);
    }
}
