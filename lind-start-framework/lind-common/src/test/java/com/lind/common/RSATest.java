package com.lind.common;

import com.lind.common.encrypt.RSAUtils;
import org.junit.Test;
import org.springframework.util.Base64Utils;

import java.security.KeyPair;

public class RSATest {
    @Test
    public void test() throws Exception {
        // 生成密钥对
        KeyPair keyPair = RSAUtils.getKeyPair();
        String privateKey = new String(Base64Utils.encode(keyPair.getPrivate().getEncoded()));
        String publicKey = new String(Base64Utils.encode(keyPair.getPublic().getEncoded()));
        System.out.println("私钥:" + privateKey);
        System.out.println("公钥:" + publicKey);
        // RSA加密
        String data = "123456";
        String encryptData = RSAUtils.encrypt(data, RSAUtils.getPublicKey(publicKey));
        System.out.println("加密后内容:" + encryptData);
        // RSA解密
        String decryptData = RSAUtils.decrypt(encryptData, RSAUtils.getPrivateKey(privateKey));
        System.out.println("解密后内容:" + decryptData);
        // RSA签名
        String sign = RSAUtils.sign(data, RSAUtils.getPrivateKey(privateKey));
        // RSA验签
        boolean result = RSAUtils.verify(data, RSAUtils.getPublicKey(publicKey), sign);
        System.out.print("验签结果:" + result);
    }
}
