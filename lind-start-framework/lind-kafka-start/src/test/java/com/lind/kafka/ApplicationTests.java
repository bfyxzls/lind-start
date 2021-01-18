package com.lind.kafka;

import com.lind.kafka.entity.MessageEntity;
import com.lind.kafka.handler.FailureHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationTests {
    @KafkaListener(topics = "demo")
    public void messageReceive(String message) {
        System.out.println(message);
    }
}