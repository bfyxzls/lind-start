package com.lind.common.util;

import com.sun.jndi.toolkit.url.Uri;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;

/**
 * apache http get,post方法封装
 */
public class HttpUtils {
    static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * post请求.
     *
     * @return
     */
    public static String post(String url) {
        String resultStr = "";
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String path = getEncodeUrl(url);
            HttpPost method = new HttpPost(path);
            CloseableHttpResponse result = httpClient.execute(method);
            resultStr = EntityUtils.toString(result.getEntity());
        } catch (Exception e) {
            log.error("post错误信息:", e);
        }
        return resultStr;
    }

    /**
     * get请求.
     */
    public static String get(String url) {
        String result = "";
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            String path = getEncodeUrl(url);
            HttpGet httpget = new HttpGet(path);

            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                if (response != null
                        && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = response.getEntity();
                    result = readResponse(entity, "UTF-8");
                }
            } finally {
                httpclient.close();
                response.close();
            }
        } catch (Exception e) {
            log.error("get错误信息:", e);
        }
        return result;
    }

    /**
     * 得到编码后的url.
     *
     * @param url
     * @return
     * @throws MalformedURLException
     * @throws UnsupportedEncodingException
     */
    private static String getEncodeUrl(String url) throws MalformedURLException, UnsupportedEncodingException {
        Uri uri = new Uri(url);
        String path = uri.getScheme() + "://" + uri.getHost() + ":" + uri.getPort() + uri.getPath();
        String query = uri.getQuery();
        StringBuffer formatQuery = new StringBuffer();
        if (query != null) {

            String[] parapms = query.split("&");

            for (int i = 0; i < parapms.length; i++) {
                String[] item = parapms[i].split("=");
                if (item != null && item.length > 1) {
                    String encodeParam = item[0] + "=" + URLEncoder.encode(item[1], "utf-8") + "&";
                    if (i == parapms.length - 1) {
                        encodeParam = encodeParam.substring(0, encodeParam.length() - 1);
                    }
                    formatQuery.append(encodeParam);
                }
            }
        }
        path = path + formatQuery.toString();
        return path;
    }


    /**
     * 响应体封装.
     *
     * @param entity
     * @param charset
     * @return
     */
    private static String readResponse(HttpEntity entity, String charset) {
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

}
