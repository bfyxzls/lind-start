package com.lind.lindmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @author lind
 * @date 2022/8/17 17:12
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
public class MailController {
    private final TemplateEngine templateEngine;

    private String template = "classpath:/META-INF/spring/mail.html";

    protected String getBody(Context ctx) {
        return this.templateEngine.process(this.template, ctx);
    }

    @GetMapping("/mail/send")
    public String send() {
        Context ctx = new Context();
        ctx.setVariable("message", "zhangsan");
        return getBody(ctx);
    }
}
