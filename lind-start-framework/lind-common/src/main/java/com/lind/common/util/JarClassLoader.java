package com.lind.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.system.ApplicationHome;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

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
        LOGGER.debug("getMainJarPath:{}", path);
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
    public static <U> U joinOuterJarClass(String packageUrl, Class<U> clazz, String name) {
        try {
            if (!packageUrl.startsWith("file:/")) {
                packageUrl = getMainJarPath().concat(packageUrl);
            }
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

}