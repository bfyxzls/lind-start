package com.lind.common.locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * @ClassName MyLocaleResolverConfig
 * @Description 自定义本地国际化
 * /resources/i18n/messages.properties文件
 **/
@Component
public class MyLocaleResolverConfig implements LocaleResolver {

    private static final String PATH_PARAMETER = "lang";

    private static final String PATH_PARAMETER_SPLIT = "_";

    private static final String LANG_SESSION = "lang_session";

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String lang = request.getHeader(PATH_PARAMETER);
        Locale locale = request.getLocale();
        if (StringUtils.isNotBlank(lang)) {
            String[] split = lang.split(PATH_PARAMETER_SPLIT);
            locale = new Locale(split[0], split[1]);

            HttpSession session = request.getSession();
            session.setAttribute(LANG_SESSION, locale);
        } else {
            HttpSession session = request.getSession();
            Locale localeInSession = (Locale) session.getAttribute(LANG_SESSION);
            if (localeInSession != null) {
                locale = localeInSession;
            }
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }

    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolverConfig();
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n.message");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
