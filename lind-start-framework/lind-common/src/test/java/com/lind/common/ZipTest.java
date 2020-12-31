package com.lind.common;

import com.lind.common.encrypt.AESNetUtils;
import com.lind.common.zip.DeflaterUtils;
import com.lind.common.zip.LZString;
import com.lind.common.zip.LZW;
import com.lind.common.zip.RLEUtils;
import lombok.SneakyThrows;
import org.junit.Test;

public class ZipTest {

    @Test
    public void deflater() {
        String base64Input = "e6d77akTgmWrDaKTO86yCY/pYs4tJd/6EkSFdeW6S3A=";
        System.out.println(DeflaterUtils.zipString(base64Input));
    }

    @SneakyThrows
    @Test
    public void aes_base16_lz() {
        String str = "12345678_f_20200612_D_100_V6";
        String key = "225E8C70688FD19C5C01A212302322ED";
        String aes = AESNetUtils.encrypt(str, key);
        String result = LZString.compress(aes);
        System.out.println("code=" + result);
        System.out.println("rle=" + LZString.decompress(result));

    }

    @SneakyThrows
    @Test
    public void rle() {
        String code = "DBDCDDDEDFDGDHDIFPGGFPDCDADCDADADGDBDCFPEEFPDBDADAFPFGDG";
        System.out.println("rle=" + RLEUtils.encode(code));
    }

    @SneakyThrows
    @Test
    public void lzwUtils() {
        String code = "abcaaabbbb";
        System.out.println("rle=" + LZW.compress(code));
        System.out.println("rle=" + LZW.decompress(LZW.compress(code)));
    }
}