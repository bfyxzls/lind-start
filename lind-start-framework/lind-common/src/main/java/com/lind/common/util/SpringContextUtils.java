package com.lind.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 直接获取bean.
 */
@Component("springContextUtils")
public class SpringContextUtils implements ApplicationContextAware {

    /**
     * 上下文对象实例.
     */
    private static ApplicationContext applicationContext;

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

    /**
     * 设置上下文.
     *
     * @param applicationContext
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
