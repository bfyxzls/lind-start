package com.lind.start.test.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {
    String kafkaTopic = "topic.quick.demo";
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String message) {

        kafkaTemplate.send(kafkaTopic, message);
    }
}
