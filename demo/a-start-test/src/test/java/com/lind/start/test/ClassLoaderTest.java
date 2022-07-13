package com.lind.start.test;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Test
    public void dateToStr(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.print(sdf.format(new Date()));
    }
}
