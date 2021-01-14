package com.lind.common;

import lombok.SneakyThrows;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JarClassLoaderTest {
    /**
     * 不同类加载类产生的对象是不同的,即使是同一个类,不同的加载器产生的也是不相等的.
     */
    @Test
    public void classLoaderTest() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader loader = new ClassLoader() {
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
        Object obj = loader.loadClass("com.lind.common.JarClassLoaderTest").newInstance();
        System.out.println(obj instanceof JarClassLoaderTest);
        assertFalse(obj instanceof JarClassLoaderTest);
        JarClassLoaderTest self = new JarClassLoaderTest();
        assertTrue(self instanceof JarClassLoaderTest);

    }

    /**
     * 从将类加载器的类，添加到系统类加载器，有可能有两个同名的类出现.
     */
    @Test
    public void loadSameClassFromBothClassLoad() throws ClassNotFoundException, IllegalAccessException, NoSuchFieldException {

        printClassList();

        final Class<?>[] parameters = new Class[]{URL.class};
        String name = "D:\\github\\lind-start\\lind-start-framework\\lind-common\\target\\lind-common-1.0.0.jar";
        URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Class<?> sysclass = URLClassLoader.class;
        try {
            Method method = sysclass.getDeclaredMethod("addURL", parameters);
            method.setAccessible(true);
            method.invoke(sysloader, new Object[]{new File(name).toURI().toURL()});
        } catch (Throwable t) {
            t.printStackTrace();
        }
        for (URL url : sysloader.getURLs()) {
            System.out.println(url.toString());
        }
        sysloader.loadClass("com.lind.common.CommonApplication");//加载到内存
        printClassList();

    }

    @SneakyThrows
    void printClassList() {
        Field field = ClassLoader.class.getDeclaredField("classes");
        field.setAccessible(true);//设置该成员变量为可访问
        System.out.println(field.get(ClassLoader.getSystemClassLoader()));
    }
}
