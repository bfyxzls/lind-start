package com.lind.hot.deploy;

import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 自定义类加载器.
 */
public class RsaClassLoader {
    /**
     * 读取包
     *
     * @param packageUrl 包的绝对路径.
     * @param name       类名称.
     * @return
     * @throws ClassNotFoundException
     */
    @SneakyThrows
    public <T> T findClassLoader(String packageUrl, Class<T> classze, String name) throws ClassNotFoundException {
        URL url = new URL(packageUrl);
        ClassLoader loader = new URLClassLoader(new URL[]{url}) {
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
        return classze.cast(loader.loadClass(name).newInstance());
    }

}