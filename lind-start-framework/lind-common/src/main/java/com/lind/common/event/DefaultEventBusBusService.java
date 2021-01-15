package com.lind.common.event;

import com.lind.common.typetools.TypeResolver;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * spring ioc管理的对象本身就是单例对象.
 * 说明：同一个UserEventListener可以订阅到不同的事件类型里,但一种事件类型只能有一个事件订阅者.
 */
@Component
public class DefaultEventBusBusService implements EventBusService {

    /**
     * 事件字典.
     * 第一层事件源类型，第二层事件处理程序
     */
    private static ConcurrentHashMap<String, List<EventBusListener>> handlers = new ConcurrentHashMap<>();

    /**
     * 获取EventBusListener接口的泛型类型.
     *
     * @param userEventListener
     * @return
     */
    String getEventEntityName(EventBusListener userEventListener) {
        List<Type> a = Arrays.asList(userEventListener.getClass().getGenericInterfaces());
        Type mainType = a.stream().filter(o -> o.equals(EventBusListener.class)
                || ((ParameterizedTypeImpl)o).getRawType().equals(EventBusListener.class))
                .findFirst()
                .orElse(null);
        if (mainType == null) {
            throw new IllegalArgumentException("listener must implement EventBusListener.");
        }
        Type temp;
        if (mainType instanceof ParameterizedType) {
            temp = ((ParameterizedType) mainType).getActualTypeArguments()[0];
        } else {
            temp = TypeResolver.resolveRawArgument(EventBusListener.class, userEventListener.getClass());
        }
        return temp.getTypeName();

    }

    /**
     * 添加订阅,同一个订阅者可以订阅多种类型;同一个类型也可以被多个订阅者订阅.
     *
     * @param userEventListener
     */
    @Override
    public void addEventListener(EventBusListener userEventListener) {
        String genericType = getEventEntityName(userEventListener);
        List<EventBusListener> userEventListeners = handlers.get(genericType);
        if (CollectionUtils.isEmpty(userEventListeners)) {
            userEventListeners = new ArrayList<>();
        }
        userEventListeners.add(userEventListener);
        handlers.put(genericType, userEventListeners);
    }

    /**
     * 发布事件.
     *
     * @param abstractEvent
     */
    @Override
    public void publisher(AbstractEvent abstractEvent) {
        List<EventBusListener> eventBusListeners = handlers.get(abstractEvent.getClass().getName());
        if (!CollectionUtils.isEmpty(eventBusListeners)) {
            System.out.println("event:" + abstractEvent);
            for (EventBusListener handler : eventBusListeners) {
                System.out.println("handler:" + handler);
                handler.onEvent(abstractEvent);
            }
        }
    }
}