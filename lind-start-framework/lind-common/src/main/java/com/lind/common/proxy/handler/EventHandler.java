package com.lind.common.proxy.handler;


import com.lind.common.proxy.MessageEntity;

/**
 * 发送成功的回调函数
 *
 * @author BD-PC220
 */
public interface EventHandler {


    /**
     * 发送成功
     *
     * @param messageEntity kafka返回结果
     */
    void onSuccess(String topic, MessageEntity messageEntity);

    /**
     * 失败回调函数
     *
     * @param topic
     * @param messageEntity
     * @param ex
     */
    void onFailure(String topic, MessageEntity messageEntity, Throwable ex);
}
