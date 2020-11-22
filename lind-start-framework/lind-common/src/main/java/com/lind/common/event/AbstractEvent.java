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
    private static final long serialVersionUID = 1L;
    private final String id = UUID.randomUUID().toString();
}
