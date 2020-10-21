package com.lind.common.event;

public interface EventBusService {
    void addEventListener(EventBusListener userEventListener, String... types);

    void publisher(AbstractEvent userEvent, String type);
}
