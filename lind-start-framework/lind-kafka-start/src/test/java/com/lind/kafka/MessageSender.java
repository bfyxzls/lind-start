package com.lind.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {
    public static String kafkaTopic = "topic.quick.demo";
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String message) {
        kafkaTemplate.send(kafkaTopic, message);
        System.out.println("send message:" + message);
    }
}
