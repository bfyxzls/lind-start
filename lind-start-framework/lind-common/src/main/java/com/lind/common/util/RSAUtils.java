package com.lind.common.util;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA非对称加密算法
 * RSA加密对明文的长度有所限制，规定需加密的明文最大长度=密钥长度-11（单位是字节，即byte），所以在加密和解密的过程中需要分块进行。
 * 而密钥默认是1024位，即1024位/8位-11=128-11=117字节。所以默认加密前的明文最大长度117字节，解密密文最大长度为128字。那么为啥两者相差11字节呢？
 * 是因为RSA加密使用到了填充模式（padding），即内容不足117字节时会自动填满，用到填充模式自然会占用一定的字节，而且这部分字节也是参与加密的。
 */
public class RSAUtils {
    /**
     * 签名算法.
     */
    public static final String SIGN_ALGORITHMS = "MD5WithRSA";
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;
    private static final String ALGORITHM_NAME = "RSA";

    /**
     * 获取密钥对
     *
     * @return java.security.KeyPair
     */
    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM_NAME);
        generator.initialize(1024);
        return generator.generateKeyPair();
    }

    /**
     * 获取私钥
     *
     * @param privateKey 私钥字符串
     * @return java.security.PrivateKey
     */
    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME);
        byte[] decodedKey = Base64Utils.decode(privateKey.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 获取公钥
     *
     * @param publicKey 公钥字符串
     * @return java.security.PublicKey
     */
    public static PublicKey getPublicKey(String publicKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME);
        byte[] decodedKey = Base64Utils.decode(publicKey.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * RSA加密
     *
     * @param data      待加密数据
     * @param publicKey 公钥
     * @return java.lang.String
     */
    public static String encrypt(String data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM_NAME);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int inputLen = data.getBytes().length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data.getBytes(), offset, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data.getBytes(), offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        // 获取加密内容使用base64进行编码,并以UTF-8为标准转化成字符串
        // 加密后的字符串
        return new String(Base64Utils.encode(encryptedData));
    }

    /**
     * RSA解密
     *
     * @param data       待解密数据
     * @param privateKey 私钥
     * @return java.lang.String
     */
    public static String decrypt(String data, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM_NAME);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] dataBytes = Base64.decodeBase64(data);
        int inputLen = dataBytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        //对数据分段解密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(dataBytes, offset, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(dataBytes, offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        // 解密后的内容
        return new String(decryptedData, StandardCharsets.UTF_8);
    }

    /**
     * 签名
     *
     * @param data       待签名数据
     * @param privateKey 私钥
     * @return java.lang.String
     */
    public static String sign(String data, PrivateKey privateKey) throws Exception {
        byte[] keyBytes = privateKey.getEncoded();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME);
        PrivateKey key = keyFactory.generatePrivate(keySpec);
        Signature signature = Signature.getInstance(SIGN_ALGORITHMS);

        signature.initSign(key);
        signature.update(data.getBytes());
        return new String(Base64Utils.encode(signature.sign()));
    }

    /**
     * 验签
     *
     * @param srcData   原始字符串
     * @param publicKey 公钥
     * @param sign      签名
     * @return boolean 是否验签通过
     */
    public static boolean verify(String srcData, PublicKey publicKey, String sign) throws Exception {
        byte[] keyBytes = publicKey.getEncoded();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME);
        PublicKey key = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
        signature.initVerify(key);
        signature.update(srcData.getBytes());
        return signature.verify(Base64Utils.decode(sign.getBytes()));
    }
}
