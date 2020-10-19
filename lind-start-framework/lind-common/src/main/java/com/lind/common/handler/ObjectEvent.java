package com.lind.common.handler;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

/**
 * email事件源对象
 */
@Getter
@Setter
public class ObjectEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id = UUID.randomUUID().toString();
}
