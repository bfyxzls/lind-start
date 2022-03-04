package com.lind.common.encrypt;

import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.util.Base64Utils;

import java.io.FileNotFoundException;
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

  @SneakyThrows
  @Test
  public void testWindows() throws FileNotFoundException {

    String data = "hCNAgckWUvIl8uX19UrWbzh7LOcOf7OXAQ8dyqK+ss3sBCxJQu6esmE23nSgs/9f3oWCyWeLmOQi4l16qbh5leDqa61kjcs7HVyXXi8kXJ23qt82RqDW+yPCoYWXQQH+dcwKJXeN55nH6Er5+g1RT++yPaEFHfbotDuVL/+l0qyv15rAsHHTPb21hNYwxcAgN5fdfsxCffxLpLIoLE+aXosWCSVmyBGFnsFQsFaELq1OvR6nyIQXEqMU408VuRXGILmQmxpWVZ2HwqJq8bhkfcsVkhZilSK2Ut2qHuRgahC88t2XsTNLfugbb+SGyV83Vac2rkpc7AeSQSHe1DlAf8ymA/lS9ddd9EQYHZnOdD6fKqpLaHtSTyTTt3CLspyaP9kvMDSj5KxDHV2gJnpIXFJwMtr894FfzuKLLbXdw+5EsDsEbRqyM8pASZ2+UT7/zmA/yYBnu8/5AmW94halfiUqzmHqooU8WULBdcKV+quTEUyPFfL1hXuzAvBjOR/Y4wJ0xtzxhEvOjNBYH+f5W7JkYnvnuzXYv20Zfb8wn8U4ZU2n43oUXNnH12y5fVkzU6KXjyeKsST9+1rF482j+lK7cII34qVI7eiiXgIh54Ci8MfGaV6VOXCnc7xkLYZ1tg/y+q+B3OZZ/2hOYzDAzdv8MiRqI9AkbFISFPD8mUE=";
    String uid = RSAUtils.decrypt(data, RSAUtils.getPrivateKey("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"));
    System.out.println(uid);
  }
}
