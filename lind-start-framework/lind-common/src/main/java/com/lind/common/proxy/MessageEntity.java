package com.lind.common.proxy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 消息体
     */
    private T data;

    /**
     * 发送消息的用户名
     */
    private String sendUser;

    /**
     * 发送时间
     */
    private Date sendTime;
}
