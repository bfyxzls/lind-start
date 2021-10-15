package com.lind.start.test.controller;

import com.lind.common.util.CookieUtils;
import org.apache.tomcat.util.http.SameSiteCookies;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cookie")
public class CookieController {
  @GetMapping("write")
  public ResponseEntity write() {
    CookieUtils.addCookie("one", "zzl");
    CookieUtils.addCookie("two", "zzl", true, SameSiteCookies.NONE);

    return ResponseEntity.ok("success");
  }
}
