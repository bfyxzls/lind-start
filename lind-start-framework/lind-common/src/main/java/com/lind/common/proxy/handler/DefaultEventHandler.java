package com.lind.common.proxy.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lind.common.proxy.MessageEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 赵阳
 * @Description TODO
 * @date 2020/7/7 11:30
 **/
@Slf4j
@RequiredArgsConstructor
public class DefaultEventHandler implements EventHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onSuccess(String topic, MessageEntity messageEntity) {
        log.info("sent topic={} data={} with offset={} partition={}", topic, messageEntity);
    }

    @Override
    public void onFailure(String topic, MessageEntity messageEntity, Throwable ex) {
        log.error("unable to send topic={}, data={}", topic, messageEntity, ex);
    }
}
