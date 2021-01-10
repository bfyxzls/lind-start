package com.lind.common.util;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.system.ApplicationHome;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/**
 * 外部Jar类型加载器.
 */
public class JarClassLoader {

    final static Logger LOGGER = LoggerFactory.getLogger(JarClassLoader.class);

    /**
     * 获取jar包所在路径URL格式
     *
     * @return jar包所在路径
     */
    public static String getMainJarPath() {
        ApplicationHome home = new ApplicationHome(JarClassLoader.class);
        String path = home.getSource().toURI().toString();
        if (path.endsWith(".jar")) {
            path = path.substring(0, path.lastIndexOf("/") + 1);
        }
        LOGGER.info("getMainJarPath:{}", path);
        return path;
    }

    /**
     * 读取包
     *
     * @param packageUrl 包的相对路径，应该是和当前运行的jar在同级或者下级目录
     * @param clazz
     * @param name       类名
     * @return
     * @throws ClassNotFoundException
     */
    public static <U> U joinOuterJarClass(Class<U> clazz, String packageUrl, String name) {
        try {

            LOGGER.info("packageUrl:{}", packageUrl);
            URL url = new URL(packageUrl);
            // IDEA调试时与java运行时的ClassLoader是不同的,所以需要使用当前环境下的ClassLoader
            ClassLoader loader = new URLClassLoader(new URL[]{url}, clazz.getClassLoader()) {
                @Override
                public Class<?> loadClass(String name) throws ClassNotFoundException {
                    try {
                        String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                        InputStream is = getClass().getResourceAsStream(fileName);
                        if (is == null) {
                            return super.loadClass(name);
                        }
                        byte[] b = new byte[is.available()];
                        is.read(b);
                        return defineClass(name, b, 0, b.length);

                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new ClassNotFoundException(name);
                    }
                }
            };
            Object obj = loader.loadClass(name).newInstance();
            for (Class<?> c : obj.getClass().getInterfaces()) {
                LOGGER.info(c.getName());
            }
            return clazz.cast(obj);
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("findClassLoader.error");
    }

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
            Method add = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});
            add.setAccessible(true);
            add.invoke(loader, targetUrl);
        }
    }

    /**
     * 获取指定类型的实现
     *
     * @param clazz
     * @param classLoader
     * @param <U>
     * @return
     */
    public static <U> List<U> getService(Class<U> clazz, ClassLoader classLoader) {
        ServiceLoader<U> load = ServiceLoader.load(clazz, classLoader);
        List<U> list = new ArrayList<>();
        Iterator<U> loadIterator = load.iterator();
        while (loadIterator.hasNext()) {
            list.add(loadIterator.next());
        }
        return list;
    }

}