package com.lind.common;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

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
}
