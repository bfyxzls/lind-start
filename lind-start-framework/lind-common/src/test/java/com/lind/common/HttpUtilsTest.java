package com.lind.common;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.lind.common.util.HttpUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class HttpUtilsTest {

    static final String gbk_url = "http://47.94.105.138:11814/?cmd=query&name=spider.wenshuwang&returnnum=1&filter={\"sourceurl\":\"ecaf5a00dac44f0aac43ad76018a115a\"}";


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
        log.info(new String(response.bodyBytes(), "UTF-8"));
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
}
