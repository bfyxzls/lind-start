package com.lind.common.event;

/**
 * 事件订阅需要实现这个接口.
 */
public interface EventBusListener<T extends AbstractEvent> {
    /**
     * Called when an com.lind.common.event has been fired
     *
     * @param event the com.lind.common.event
     */
    void onEvent(T event);
}
