package com.lind.kafka;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lind.kafka.entity.MessageEntity;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListenerTest {
    @Autowired
    ObjectMapper objectMapper;

    @SneakyThrows
    @KafkaListener(topics = "demo", groupId = "default")
    public void messageReceive(String message) {
        MessageEntity<UserDTO> userDTOMessageEntity = objectMapper.readValue(
                message,
                new TypeReference<MessageEntity<UserDTO>>() {
                });
        System.out.println(userDTOMessageEntity.getData());
    }
}