package com.lind.spi;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

/**
 * spi插件加载器.
 */
public class SpiFactory {

    /**
     * Parameters of the method to add an URL to the System classes.
     */
    private static final Class<?>[] parameters = new Class[]{URL.class};

    /**
     * Adds a file to the classpath.
     *
     * @param s a String pointing to the file
     * @throws IOException
     */
    public static void addFile(String s) throws IOException {
        File f = new File(s);
        addFile(f);
    }

    /**
     * Adds a file to the classpath
     *
     * @param f the file to be added
     * @throws IOException
     */
    public static void addFile(File f) throws IOException {
        addURL(f.toURI().toURL());
    }

    /**
     * 加载jar到当前的classLoader.
     *
     * @param u the URL pointing to the content to be added
     * @throws IOException
     */
    public static void addURL(URL u) throws IOException {
        URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Class<?> sysclass = URLClassLoader.class;
        try {
            Method method = sysclass.getDeclaredMethod("addURL", parameters);
            method.setAccessible(true);
            method.invoke(sysloader, new Object[]{u});
        } catch (Throwable t) {
            t.printStackTrace();
            throw new IOException("Error, could not add URL to system classloader");
        }
    }


    /**
     * 从具体的providerFactory中得到指定的providerFactory.
     *
     * @param clazz
     * @param id
     * @param classLoader
     * @param <U>
     * @return
     */
    public static <U extends ProviderFactory> U getProviderFactory(Class<U> clazz, String id, ClassLoader classLoader) {
        ServiceLoader<U> load = ServiceLoader.load(clazz, classLoader);
        for (U providerFactory : load) {
            System.out.println("getProviderFactory:" + providerFactory.getId());
        }
        for (U providerFactory : load) {
            if (providerFactory.getId().toLowerCase().equals(id.toLowerCase())) {
                return providerFactory;
            }
        }
        return null;
    }

    /**
     * 返回所有具体的providerFactory列表.
     *
     * @param clazz
     * @param classLoader
     * @param <U>
     * @return
     */
    public static <U extends ProviderFactory> List<U> getProviderFactory(Class<U> clazz, ClassLoader classLoader) {
        ServiceLoader<U> load = ServiceLoader.load(clazz, classLoader);
        List<U> list = new ArrayList<>();
        for (U providerFactory : load) {
            list.add(providerFactory);
        }
        return list;
    }

    /**
     * 从所有providerFactory中得到指定的providerFactory.
     *
     * @param id
     * @param classLoader
     * @return
     */
    public static ProviderFactory getProviderFactory(String id, ClassLoader classLoader) {
        return getProviderFactory(ProviderFactory.class, id, classLoader);
    }

    /**
     * 返回所有providerFactory列表.
     *
     * @param classLoader
     * @return
     */
    public static List<ProviderFactory> getProviderFactory(ClassLoader classLoader) {
        return getProviderFactory(ProviderFactory.class, classLoader);
    }
}
