package com.lind.start.test;

import com.lind.common.util.MapUtils;

import java.util.Map;

/**
 * @author lind
 * @date 2022/8/10 15:57
 * @since 1.0.0
 */
public class CurrentThreadObj {
    static final InheritableThreadLocal<Map<String, Object>> inheritableThreadLocal = new InheritableThreadLocal<>();

    public static void set(String key, Object val) {
        if (inheritableThreadLocal.get() == null) {
            inheritableThreadLocal.set(MapUtils.<String, Object>hashMapBuilder(8).put(key, val).build());
        } else {
            inheritableThreadLocal.get().put(key, val);
        }
    }

    public static Object get(String key) {
        if (inheritableThreadLocal.get() == null) {
            return null;
        }
        return inheritableThreadLocal.get().get(key);
    }
}
