package com.lind.lindmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

/**
 * @author lind
 * @date 2022/8/17 17:12
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
public class MailController {
    private final SpringTemplateEngine springTemplateEngine;
    private String template = "META-INF/spring/mail.html";

    /**
     * 发送email.
     *
     * @return
     */
    @GetMapping("/mail/send")
    public String send() {
        Context ctx = new Context();
        ctx.setVariable("message", "zhangsan");
        return springTemplateEngine.process(this.template, ctx);
    }
}
