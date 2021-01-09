package com.lind.hot.deploy.spi;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


/**
 * 类描述 动态加载外部jar包的自定义类加载器
 *
 * @version 1.0
 * @fileName ModuleClassLoader.java
 * @date 2019-06-21 10:22
 */
public class ModuleClassLoader extends URLClassLoader {

    //属于本类加载器加载的jar包
    private JarFile jarFile;

    //保存已经加载过的Class对象
    private final Map<String, Class> cacheClassMap = new HashMap<>();

    //保存本类加载器加载的class字节码
    private final Map<String, byte[]> classBytesMap = new HashMap<>();

    //需要注册的spring bean的name集合
    private final List<String> registeredBean = new ArrayList<>();


    //构造
    public ModuleClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
        URL url = urls[0];
        String path = url.getPath();
        try {
            jarFile = new JarFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //初始化类加载器执行类加载
        init();
    }


    //重写loadClass方法
    //改写loadClass方式
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (findLoadedClass(name) == null) {
            return super.loadClass(name);
        } else {
            return cacheClassMap.get(name);
        }

    }


    /**
     * 方法描述 初始化类加载器，保存字节码
     *
     * @method init
     */
    private void init() {

        //解析jar包每一项
        Enumeration<JarEntry> en = jarFile.entries();
        InputStream input = null;
        try {
            while (en.hasMoreElements()) {
                JarEntry je = en.nextElement();
                String name = je.getName();
                //这里添加了路径扫描限制
                if (name.endsWith(".class")) {
                    String className = name.replace(".class", "").replaceAll("/", ".");
                    input = jarFile.getInputStream(je);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int bufferSize = 4096;
                    byte[] buffer = new byte[bufferSize];
                    int bytesNumRead = 0;
                    while ((bytesNumRead = input.read(buffer)) != -1) {
                        baos.write(buffer, 0, bytesNumRead);
                    }
                    byte[] classBytes = baos.toByteArray();
                    classBytesMap.put(className, classBytes);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        //将jar中的每一个class字节码进行Class载入
        for (Map.Entry<String, byte[]> entry : classBytesMap.entrySet()) {
            String key = entry.getKey();
            Class<?> aClass = null;
            try {
                aClass = loadClass(key);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            cacheClassMap.put(key, aClass);
        }

    }

    //获取当前类加载器注册的bean
    //在移除当前类加载器的时候需要手动删除这些注册的bean
    public List<String> getRegisteredBean() {
        return registeredBean;
    }


    /**
     * 方法描述 判断class对象是否带有spring的注解
     *
     * @param cla jar中的每一个class
     * @return true 是spring bean   false 不是spring bean
     * @method isSpringBeanClass
     */
    public boolean isSpringBeanClass(Class<?> cla) {
        if (cla == null) {
            return false;
        }
        //是否是接口
        if (cla.isInterface()) {
            return false;
        }

        //是否是抽象类
        if (Modifier.isAbstract(cla.getModifiers())) {
            return false;
        }

        if (cla.getAnnotation(Component.class) != null) {
            return true;
        }
        if (cla.getAnnotation(Repository.class) != null) {
            return true;
        }
        return cla.getAnnotation(Service.class) != null;
    }

}