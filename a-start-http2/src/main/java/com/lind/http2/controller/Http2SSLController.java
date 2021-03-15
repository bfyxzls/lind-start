package com.lind.http2.controller;

import cn.hutool.core.io.FileUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class Http2SSLController {
    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @SneakyThrows
    @GetMapping("/ok")
    public ResponseEntity ok() {
        String path = "D:\\test\\";
        List<String> names = FileUtil.listFileNames(path);
        List<Protocol> protocols = new ArrayList<>();
        protocols.add(Protocol.HTTP_1_1); // 这里如果，只指定h2的话会抛异常
        protocols.add(Protocol.HTTP_2); // 这里如果，只指定h2的话会抛异常
        for (String name : names) {
            String url = "https://localhost:8443/get-file?path=" + name;
            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .build();//配置
            final Request request = new Request.Builder()
                    .url(url)
                    .get()//默认就是GET请求，可以不写
                    .build();
            Call call = okHttpClient.newCall(request);


            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    log.error("onFailure:{}", e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    ResponseBody body = response.body();
                    if (body != null) {
                        log.info("success body:{}", new String(body.bytes()));
                        body.close();
                    }
                }
            });

        }

        return ResponseEntity.ok("success");
    }
}
