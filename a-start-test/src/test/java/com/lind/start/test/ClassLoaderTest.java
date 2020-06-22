package com.lind.start.test;

import org.junit.Test;

/**
 * java类加载器
 */
public class ClassLoaderTest {

    @Test
    public void classLoader() {
        ClassLoader loader = TestApplication.class.getClassLoader();
        System.out.println(loader.toString());
        System.out.println(loader.getParent().toString());
        System.out.println(loader.getParent().getParent());

    }
}
