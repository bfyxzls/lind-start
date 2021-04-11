package com.lind.common.event;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

/**
 * email事件源对象
 */
@Getter
@ToString
public abstract class AbstractEvent implements Serializable {
    /**
     * event id.
     */
    private final String id = UUID.randomUUID().toString();
    /**
     * event source name.
     */
    private String name = this.getClass().getName();
}
