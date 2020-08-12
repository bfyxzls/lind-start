package com.lind.start.test.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageListener {
    /**
     * group就是partition的概念，在rabbitmq里就是广播.
     * @param msgData
     */
    @KafkaListener(groupId = "lind1", topics = "topic.quick.demo")
    public void listen1(String msgData) {
        log.info("lind1 demo receive : " + msgData);
    }

    @KafkaListener(groupId = "lind2", topics = "topic.quick.demo")
    public void listen2(String msgData) {
        log.info("lind2 demo receive : " + msgData);
    }
}
