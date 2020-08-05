package com.lind.elasticsearch.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 直接获取bean.
 */
@Component
public class SpringContextConfig implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static <T> T getBean(Class<T> cls) {
        if (applicationContext == null) {
            throw new RuntimeException("applicationContext注入失败");
        }
        try {
            return applicationContext.getBean(cls);
        } catch (NoSuchBeanDefinitionException ex) {
            return null;
        }
    }

    public static Object getBean(String name) {
        if (applicationContext == null) {
            throw new RuntimeException("applicationContext注入失败");
        }
        try {
            return applicationContext.getBean(name);
        } catch (NoSuchBeanDefinitionException ex) {
            return null;
        }

    }

    public static <T> T getBean(String name, Class<T> cls) {
        if (applicationContext == null) {
            throw new RuntimeException("applicationContext注入失败");
        }
        try {
            return applicationContext.getBean(name, cls);
        } catch (NoSuchBeanDefinitionException ex) {
            return null;
        }
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextConfig.applicationContext = applicationContext;
    }
}
