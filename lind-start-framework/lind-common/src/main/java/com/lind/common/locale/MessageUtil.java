package com.lind.keycloak;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Locale;

/**
 * @ClassName MessageUtil
 * @Description 实质消息获取类
 * @Author yuxiang
 * @Date 2020/9/15
 * @Version 1.0
 **/
@Component
public class MessageUtil {

    private static MessageSource staticMessageSource;
    @Resource
    private MessageSource messageSource;

    public static String getMessage(String key) {
        Locale locale = LocaleContextHolder.getLocale();
        return staticMessageSource.getMessage(key, null, locale);
    }

    @PostConstruct
    public void init() {
        staticMessageSource = messageSource;
    }
}
