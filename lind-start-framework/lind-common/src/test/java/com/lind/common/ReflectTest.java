package com.lind.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author lind
 * @date 2022/8/9 17:06
 * @since 1.0.0
 */
public class ReflectTest {
    public void print(JSONObject a) {
        System.out.println(a);
    }

    /**
     * 反射调用方法.
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Test
    public void invoke() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clazz = Class.forName("com.lind.common.ReflectTest");
        Method method = clazz.getDeclaredMethod("print", JSONObject.class);
        String param = "{hello:\"ok\",sex:\"1\"}";
        JSONObject anchorParam = JSON.parseObject(param);
        method.invoke(new ReflectTest(), anchorParam);
    }
}
