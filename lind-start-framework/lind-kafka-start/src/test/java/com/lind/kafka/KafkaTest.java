package com.lind.kafka;

import com.lind.kafka.entity.MessageEntity;
import com.lind.kafka.sender.MessageSender;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class KafkaTest {
    @Autowired
    MessageSender messageSender;

    @SneakyThrows
    @Test
    public void testReceivingKafkaEvents() throws InterruptedException {
        MessageEntity testMessageEntity = new MessageEntity();
        testMessageEntity.setData("hello");
        messageSender.send("demo", testMessageEntity);
        Thread.sleep(1000);
    }
}