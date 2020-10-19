package com.lind.common.handler;

/**
 * 事件订阅需要实现这个接口.
 */
public interface ObjectEventListener<T extends ObjectEvent> {
    /**
     * Called when an event has been fired
     *
     * @param event the event
     */
    void onEvent(T event);
}
