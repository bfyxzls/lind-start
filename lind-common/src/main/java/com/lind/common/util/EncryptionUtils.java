package com.lind.common.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * 加密工具类.
 */
public class EncryptionUtils {
    private EncryptionUtils() {
        throw new AssertionError();
    }

    /**
     * BASE64解密.
     *
     * @param key .
     * @return
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (Base64.getDecoder().decode(key));
    }

    /**
     * BASE64加密.
     *
     * @param key .
     * @return
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return (Base64.getEncoder().encodeToString(key));
    }

    /**
     * MD5加载.
     *
     * @param inputStr 明文
     * @param len      长度
     * @return
     */
    public static String MD5(String inputStr, int len) {
        BigInteger bigInteger = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] inputData = inputStr.getBytes();
            md.update(inputData);
            bigInteger = new BigInteger(md.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bigInteger.toString(len);
    }

    /**
     * 长度为32位的MD5字符.
     *
     * @param inputStr 明文
     * @return
     */
    public static String MD5(String inputStr) {
        return MD5(inputStr, 32);
    }

    /**
     * DES加密.
     *
     * @param password 密钥
     * @param data     数据
     * @return
     */
    public static String encryptDES(String password, String data) {
        return DESUtils.encrypt(password, data);
    }

    /**
     * DES解密.
     *
     * @param password 密钥
     * @param data     数据
     * @return
     */
    public static String decryptDES(String password, String data) {
        return DESUtils.decrypt(password, data);
    }

    /**
     * SHA1的加密算法.
     *
     * @param inputStr 明文
     * @return
     */
    public static String SHA(String inputStr) {
        BigInteger sha = null;
        byte[] inputData = inputStr.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update(inputData);
            sha = new BigInteger(messageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sha.toString(32);
    }

    static class DESUtils {

        /**
         * 偏移变量，固定占8位字节
         */
        private final static String IV_PARAMETER = "12345678";
        /**
         * 密钥算法
         */
        private static final String ALGORITHM = "DES";
        /**
         * 加密/解密算法-工作模式-填充模式
         */
        private static final String CIPHER_ALGORITHM = "DES/CBC/PKCS5Padding";
        /**
         * 默认编码
         */
        private static final String CHARSET = "utf-8";

        /**
         * 生成key
         *
         * @param password
         * @return
         * @throws Exception
         */
        private static Key generateKey(String password) throws Exception {
            DESKeySpec dks = new DESKeySpec(password.getBytes(CHARSET));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            return keyFactory.generateSecret(dks);
        }


        /**
         * DES加密字符串
         *
         * @param password 加密密码，长度不能够小于8位
         * @param data     待加密字符串
         * @return 加密后内容
         */
        public static String encrypt(String password, String data) {
            if (password == null || password.length() < 8) {
                throw new RuntimeException("加密失败，key不能小于8位");
            }
            if (data == null)
                return null;
            try {
                Key secretKey = generateKey(password);
                Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
                IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(CHARSET));
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
                byte[] bytes = cipher.doFinal(data.getBytes(CHARSET));
                return new String(Base64.getEncoder().encode(bytes));

            } catch (Exception e) {
                e.printStackTrace();
                return data;
            }
        }

        /**
         * DES解密字符串
         *
         * @param password 解密密码，长度不能够小于8位
         * @param data     待解密字符串
         * @return 解密后内容
         */
        public static String decrypt(String password, String data) {
            if (password == null || password.length() < 8) {
                throw new RuntimeException("加密失败，key不能小于8位");
            }
            if (data == null)
                return null;
            try {
                Key secretKey = generateKey(password);
                Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
                IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(CHARSET));
                cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
                return new String(cipher.doFinal(
                        Base64.getDecoder().decode(
                                data.getBytes(CHARSET))), CHARSET);
            } catch (Exception e) {
                e.getStackTrace();
            }
            return null;

        }
    }
}
