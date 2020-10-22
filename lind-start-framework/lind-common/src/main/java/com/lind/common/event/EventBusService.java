package com.lind.common.event;

/**
 * 事件总线接口.
 */
public interface EventBusService {
    void addEventListener(EventBusListener userEventListener, String... types);

    void publisher(AbstractEvent userEvent, String type);
}
