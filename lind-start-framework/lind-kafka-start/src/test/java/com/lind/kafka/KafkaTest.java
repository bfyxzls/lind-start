package com.lind.kafka;

import com.lind.kafka.anno.EnableMqKafka;
import com.lind.kafka.entity.MessageEntity;
import com.lind.kafka.producer.MessageSender;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableMqKafka
//@ActiveProfiles("dev")
public class KafkaTest {
    @Autowired
    MessageSender messageSender;
    @Autowired
    MessageDataSend messageDataSend;

    @SneakyThrows
    @Test
    public void testReceivingKafkaEvents() throws InterruptedException {
        MessageEntity testMessageEntity = new MessageEntity();
        UserDTO userDTO = new UserDTO();
        userDTO.setTitle("世界你好" + new Date());
        testMessageEntity.setData(userDTO);
        messageSender.send("demo", testMessageEntity);
        Thread.sleep(10000);
    }

    @Test
    public void annoSender() {
        MessageEntity testMessageEntity = new MessageEntity();
        UserDTO userDTO = new UserDTO();
        userDTO.setTitle("世界你好" + new Date());
        testMessageEntity.setData(userDTO);
        messageDataSend.sendDataMessage(testMessageEntity);
    }
}