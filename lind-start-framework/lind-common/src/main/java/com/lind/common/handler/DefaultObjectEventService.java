package com.lind.common.handler;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * spring ioc管理的对象本身就是单例对象.
 * 说明：同一个UserEventListener可以订阅到不同的事件类型里,但一种事件类型只能有一个事件订阅者.
 */
@Component
public class DefaultObjectEventService implements ObjectEventService {

    /**
     * 事件字典.
     */
    private ConcurrentHashMap<String, Map<String, ObjectEventListener>> handlers = new ConcurrentHashMap<>();

    /**
     * 添加订阅,同一个订阅者可以订阅多种类型;同一个类型也可以被多个订阅者订阅.
     *
     * @param userEventListener
     * @param types
     */
    @Override
    public void addEventListener(ObjectEventListener userEventListener, String... types) {
        for (String userEventType : types) {
            Map<String, ObjectEventListener> userEventListeners = handlers.get(userEventType);
            if (CollectionUtils.isEmpty(userEventListeners)) {
                userEventListeners = new HashMap<>();
            }
            if (!userEventListeners.containsKey(userEventListener.getClass().getName())) {
                userEventListeners.put(userEventListener.getClass().getName(), userEventListener);
                handlers.put(userEventType, userEventListeners);
            }
        }

    }

    /**
     * 发布事件.
     *
     * @param userEvent
     * @param type
     */
    @Override
    public void publisher(ObjectEvent userEvent, String type) {
        if (handlers.containsKey(type)) {
            for (ObjectEventListener handler : handlers.get(type).values()) {
                handler.onEvent(userEvent);
            }
        }
    }
}
