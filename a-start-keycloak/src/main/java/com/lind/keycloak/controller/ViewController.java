package com.lind.keycloak.controller;

import com.lind.common.util.VerifyCodeUtils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ViewController {

    @Autowired
    VerifyCodeUtils verifyCodeUtils;

    @GetMapping("/index")
    public String index() {
        return "view/index";
    }

    @SneakyThrows
    @GetMapping("/redirect")
    public void index(HttpServletResponse response) {
        response.sendRedirect("http://192.168.3.181:9090/ok");
    }

    @GetMapping("/sifa")
    public String sifa() {
        return "view/sifa";
    }

    @GetMapping("/lvsuo")
    public String lvsuo() {
        return "view/lvsuo";
    }

    @GetMapping("/faxue")
    public String faxue() {
        return "view/faxue";
    }

    @GetMapping("/test")
    public String test() {
        return "view/test";
    }

    @SneakyThrows
    @GetMapping("/code")
    public void code(HttpServletResponse response) {
        //以JPEG格式向客户端发送
        ServletOutputStream os = response.getOutputStream();
        ImageIO.write(verifyCodeUtils.getBuffImg(), "PNG", os);
        os.flush();
        os.close();

    }
}
