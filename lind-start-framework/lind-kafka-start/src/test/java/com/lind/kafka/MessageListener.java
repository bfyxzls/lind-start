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
    @KafkaListener(id = "topic.quick.demo1", groupId = "lind1", topics = "topic.quick.demo")
    public void listen1(String msgData) {
        System.out.println("lind1  receive : " + msgData);
    }


    @KafkaListener(id = "topic.quick.demo2", groupId = "lind2", topics = "topic.quick.demo")
    public void listen2(String msgData) {
        System.out.println("lind2  receive : " + msgData);
    }

    @KafkaListener(id = "topic.quick.demo3", groupId = "lind3", topics = "topic.quick.demo")
    public void listen3(String msgData) {
        System.out.println("lind3  receive : " + msgData);
    }

}
