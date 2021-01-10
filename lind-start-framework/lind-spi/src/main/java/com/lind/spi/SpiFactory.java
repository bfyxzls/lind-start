package com.lind.spi;

import lombok.SneakyThrows;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

public class SpiFactory {

    /**
     * 将jar包添加到当前类加载器.
     *
     * @param path
     * @param clazz
     */
    @SneakyThrows
    public static void joinJar(Class clazz, String path) {
        // 将本地jar文件加载至classloader
        URLClassLoader loader = (URLClassLoader) clazz.getClassLoader();
        URL targetUrl = new URL(path);

        boolean isLoader = false;
        for (URL url : loader.getURLs()) {
            if (url.equals(targetUrl)) {
                isLoader = true;
                break;
            }
        }
        // 如果没有加载
        if (!isLoader) {
            Method add = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            add.setAccessible(true);
            add.invoke(loader, targetUrl);
        }
    }


    /**
     * 得到指定的providerFactory.
     *
     * @param clazz
     * @param id
     * @param classLoader
     * @param <U>
     * @return
     */
    public static <U extends ProviderFactory> ProviderFactory getProviderFactory(Class<U> clazz, String id, ClassLoader classLoader) {
        ServiceLoader<U> load = ServiceLoader.load(clazz, classLoader);
        Iterator<U> loadIterator = load.iterator();
        while (loadIterator.hasNext()) {
            ProviderFactory helloProviderFactory = loadIterator.next();
            System.out.println(">" + helloProviderFactory.getId());
            if (helloProviderFactory.getId().equals(id)) {
                return helloProviderFactory;
            }
        }
        return null;
    }

    /**
     * 得到所有providerFactory.
     *
     * @param clazz
     * @param classLoader
     * @param <U>
     * @return
     */
    public static <U extends ProviderFactory> List<U> getProviderFactory(Class<U> clazz, ClassLoader classLoader) {
        ServiceLoader<U> load = ServiceLoader.load(clazz, classLoader);
        List<U> list = new ArrayList<>();
        Iterator<U> loadIterator = load.iterator();
        while (loadIterator.hasNext()) {
            list.add(loadIterator.next());
        }
        return list;
    }

    /**
     * 得到所有providerFactory.
     *
     * @param id
     * @param classLoader
     * @return
     */
    public static ProviderFactory getProviderFactory(String id, ClassLoader classLoader) {
        return getProviderFactory(ProviderFactory.class, id, classLoader);
    }

    /**
     * 得到所有providerFactory.
     *
     * @param classLoader
     * @return
     */
    public static List<ProviderFactory> getProviderFactory(ClassLoader classLoader) {
        return getProviderFactory(classLoader);
    }
}
