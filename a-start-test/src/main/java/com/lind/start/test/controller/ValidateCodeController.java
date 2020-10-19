package com.lind.start.test.controller;

import com.lind.common.util.VerifyCodeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ValidateCodeController {
    @GetMapping("verify-code")
    public void code(HttpServletResponse response) throws IOException {
        //得到验证码 生成指定验证码
        String code = new VerifyCodeUtils().randomStr(4);

        VerifyCodeUtils vCode = new VerifyCodeUtils(116, 36, 4, 10, code);
        response.setContentType("image/png");
        vCode.write(response.getOutputStream());
    }
}
