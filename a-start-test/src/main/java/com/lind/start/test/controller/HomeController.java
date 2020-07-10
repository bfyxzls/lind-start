package com.lind.start.test.controller;


import com.lind.start.test.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@Slf4j
public class HomeController {
    @GetMapping("/ok")
    public ResponseEntity ok() {
        return ResponseEntity.ok("ok");
    }


    @PostMapping("/p")
    public ResponseEntity p(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userDTO);
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