package com.lind.start.test.controller;


import com.lind.common.lock.Callback;
import com.lind.common.lock.RedisLockTemplate;
import com.lind.start.test.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.util.fst.Util;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class HomeController {
    @Autowired
    private RedisLockTemplate redisLockTemplate;

    @GetMapping("/ok")
    public ResponseEntity ok() {
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/p")
    public ResponseEntity p(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/pget")
    public ResponseEntity pget() {
        return ResponseEntity.ok(UserDTO.builder()
                .name("zzl")
                .total(5d)
                .totalMoney(BigDecimal.valueOf(100))
                .build());
    }

    @GetMapping("/no-get")
    public ResponseEntity noGet() {
        return ResponseEntity.ok(UserDTO.builder()
                .name("zzl")
                .total(5d)
                .totalMoney(BigDecimal.valueOf(100))
                .build());
    }

    /**
     * 同步锁限流测试.
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/lockAndLimit", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity test1(HttpServletRequest request) {

        redisLockTemplate.execute("订单流水号", 10, TimeUnit.SECONDS, new Callback() {
            @Override
            public Object onGetLock() throws InterruptedException {
                // TODO 获得锁后要做的事
                log.info("生成订单流水号，需要5秒钟，些时有请求打入应该被阻塞或者拒绝");
                Thread.sleep(3000);
                return null;
            }

            @Override
            public Object onTimeout() throws InterruptedException {
                // TODO 未获取到锁（获取锁超时）后要做的事
                log.info("oops 没拿到锁");
                return null;
            }
        });

        return ResponseEntity.ok("success");
    }

    /**
     * 对应重定向咱们：/callback?token=abc123，咱们再把token重定向到其它网站
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/callback")
    public void callback(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String[]> params = request.getParameterMap();

        for (Map.Entry<String, String[]> param : params.entrySet()) {
            String key = param.getKey();
            String value = StringUtils.join(param.getValue());
            log.info("key={},value={}", key, value);
        }
        String token = StringUtils.join(params.get("token"));
        response.sendRedirect("http://www.baidu.com?token=" + token);
    }
}