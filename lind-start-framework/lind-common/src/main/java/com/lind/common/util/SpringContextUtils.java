package com.lind.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 直接获取bean.
 */
@Component("springContextUtils")
public class SpringContextUtils {

    private static ApplicationContext applicationContext = null;

    public static void setApplicationContext(ApplicationContext app) throws BeansException {
        applicationContext = app;
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
     * 通过class获取Bean.
     *
     * @param clazz .
     * @param <T>   .
     * @return .
     */
    public static <T> List<T> getAllBeans(Class<T> clazz) {
        String[] names = applicationContext.getBeanNamesForType(clazz);
        List<T> list = new ArrayList<>();
        for (String name : names) {
            list.add((T) applicationContext.getBean(name));
        }
        return list;
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

    /**
     * 获取某个类型下所有bean.
     *
     * @param type
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> getBeansOfType(@Nullable Class<T> type) {
        return applicationContext.getBeansOfType(type);
    }

    /**
     * 得到所有bin的名称.
     *
     * @return
     */
    public static String[] getAllBeanNames() {
        return applicationContext.getBeanDefinitionNames();
    }

}
