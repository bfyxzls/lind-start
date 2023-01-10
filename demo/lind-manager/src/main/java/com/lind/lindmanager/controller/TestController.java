package com.lind.lindmanager.controller;

import com.lind.common.util.CookieUtils;
import com.lind.lindmanager.Color;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;

@RestController
public class TestController {
    // url:/color?color=red
    @GetMapping("color")
    public ResponseEntity color(Color color) {
        return ResponseEntity.ok(color);
    }

    // url:/color/1
    @GetMapping("color1")
    public ResponseEntity color() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("redirect")
    public ResponseEntity url() {

        String redirect1="https://www.pkulaw.com/pfnl/a6bdb3332ec0adc422b1fcb995ef9bc28efc7a4640f9b30abdfb.html?keyword=(2018)民终785号&way=listView";
        //String redirect2 = "https://www.pkulaw.com/pfnl/a6bdb3332ec0adc422b1fcb995ef9bc28efc7a4640f9b30abdfb.html?keyword=%282018%29%E6%9C%80%E9%AB%98%E6%B3%95%E6%B0%91%E7%BB%88785%E5%8F%B7&way=listView#anchor-documentno";
        String redirect_uri= URLEncoder.encode(redirect1); //中文需要进行编码
        CookieUtils.addCookie("redirect_uri", redirect_uri);

        return ResponseEntity.ok().build();
    }

}
