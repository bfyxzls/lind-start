package com.lind.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

/**
 * 直接获取bean.
 */
public class SpringContextUtils {

    /**
     * 上下文对象实例.
     */
    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext application) throws BeansException {
        applicationContext = application;
    }

    /**
     * 通过name获取 Bean.
     *
     * @param name .
     * @return .
     */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    /**
     * 通过class获取Bean.
     *
     * @param clazz .
     * @param <T>   .
     * @return .
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean.
     *
     * @param name  .
     * @param clazz .
     * @param <T>   .
     * @return .
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }
}
