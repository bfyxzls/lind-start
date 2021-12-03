package com.lind.common.util;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.net.URI;
import java.nio.charset.StandardCharsets;

@Slf4j
public class HttpUtilsTest {

  static final String gbk_url = "http://47.94.105.138:11814/?cmd=query&name=spider.wenshuwang&returnnum=1&filter={\"sourceurl\":\"ecaf5a00dac44f0aac43ad76018a115a\"}";


  /**
   * 每10毫秒请求地址
   */
  @Test
  public void loopGet() {
    while (true) {
      String url = "https://devcas.pkulaw.com:18081/auth/realms/master";
      int status = HttpUtil.createGet(url).execute().getStatus();
      if (status >= 500)
        log.warn("warn:" + status);
      try {
        Thread.sleep(50);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 中文乱码的原因：
   * 1. 操作系统编码
   * 2. Jboss编码
   * 3. jvm编码
   * 4. 请求与响应编码不同导致
   */
  @SneakyThrows
  @Test
  public void testChinese() {
    log.info(HttpUtils.get("http://47.94.105.138:11814/?cmd=query&name=spider.wenshuwang&returnnum=1&filter={\"sourceurl\":\"ecaf5a00dac44f0aac43ad76018a115a\"}"));
  }

  /**
   * 正文是gbk解析后出现乱码问题，通过byte数据转utf-8是OK的
   */
  @SneakyThrows
  @Test
  public void gbk() {
    HttpResponse response = HttpRequest.post(gbk_url)
        .header("connection", "keep-alive")
        .execute();

    response.charset("utf-8");
    log.info(new String(response.bodyBytes(), StandardCharsets.UTF_8));
  }

  /**
   * 解决正文是gbk，解析后出现乱码问题
   */
  @Test
  public void gbk_slove() {
    HttpResponse httpResponse = HttpUtil.createPost("http://47.94.105.138:11814/?cmd=query&name=spider.wenshuwang&returnnum=1&filter={\"sourceurl\":\"ecaf5a00dac44f0aac43ad76018a115a\"}")
        .header("Content-Type", "application/json;charset:utf-8")
        .execute()
        .charset("UTF-8");
    System.out.println(httpResponse.body());
  }

  /**
   * 正文是utf-8的，没有乱码.
   */
  @Test
  public void utf8() {
    HttpResponse httpResponse = HttpUtil.createPost("http://47.94.105.138:11814/?cmd=query&name=spider.wenshuwang&returnnum=1&filter={\"sourceurl\":\"e51e74bd6c6d484eb430ada200fa619a\"}")
        //这个请求头.header是自己项目需要加的，可以省略
        .header("connection", "keep-alive")
        .execute()
        .charset("utf-8");
    System.out.println(httpResponse.body());
  }

  @Test(expected = IllegalArgumentException.class)
  public void urlEncodeError() {
    String url = "https://www.pkulaw.com/chl/ded30be7a11fdecbbdfb.html?keyword=股权转让所得 个人所得税管理办法";
    URI.create(url);

  }

  @SneakyThrows
  @Test()
  public void urlEncode() {
    String url = "https://www.pkulaw.com/chl/ded30be7a11fdecbbdfb.html?keyword=股权转让所得 个人所得税管理办法";
    url = url.replaceAll("\\ ", "%20");
    URI.create(url);

  }
}
