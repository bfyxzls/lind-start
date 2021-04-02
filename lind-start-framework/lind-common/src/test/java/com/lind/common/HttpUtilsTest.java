package com.lind.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Slf4j
public class HttpUtilsTest {

    public static String get(String url) {
        String result = "";
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                if (response != null && response.getStatusLine().getStatusCode()
                        == HttpStatus.SC_OK) {
                    System.out.println(response.getStatusLine());
                    HttpEntity entity = response.getEntity();
                    System.out.println(entity.getContentEncoding());
                    result = readResponse(entity, "UTF-8");
                }
            } finally {
                httpclient.close();
                response.close();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }

    public static String readResponse(HttpEntity entity, String charset) {
        StringBuffer res = new StringBuffer();
        BufferedReader reader = null;
        try {
            if (entity == null) {
                return null;
            } else {
                reader = new BufferedReader(new InputStreamReader(entity.getContent(), charset));
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line + "\n";
                    res.append(line);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                e.toString();
            }
        }
        return res.toString();
    }

    /**
     * 中文乱码的原因：
     * 1. 操作系统编码
     * 2. Jboss编码
     * 3. jvm编码
     * 4. 请求与响应编码不同导致
     */
    @Test
    public void testChinese() {
        log.info(get("https://a1.cnblogs.com/group/T2"));
    }

}
