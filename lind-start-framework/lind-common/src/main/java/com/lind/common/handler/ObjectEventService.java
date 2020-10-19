package com.lind.common.handler;

public interface ObjectEventService {
    void addEventListener(ObjectEventListener userEventListener, String... types);

    void publisher(ObjectEvent userEvent, String type);
}
