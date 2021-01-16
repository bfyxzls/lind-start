package com.lind.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    /**
     * group分组，在rabbitmq里就是广播.
     *
     * @param msgData
     */
    @KafkaListener(topics = "demo")
    public void listen1(String msgData) {
        System.out.println("lind1  receive : " + msgData);
    }

}
