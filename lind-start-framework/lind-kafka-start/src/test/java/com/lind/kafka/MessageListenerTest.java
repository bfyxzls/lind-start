package com.lind.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListenerTest {
    @KafkaListener(topics = "demo", groupId = "default")
    public void messageReceive(String message) {
        System.out.println(message);
    }
}