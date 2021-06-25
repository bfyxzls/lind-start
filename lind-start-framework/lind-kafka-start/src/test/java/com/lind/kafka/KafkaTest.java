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

    @SneakyThrows
    @Test
    public void sleep1000() {
        MessageEntity testMessageEntity = new MessageEntity();
        UserDTO userDTO = new UserDTO();
        userDTO.setTitle("世界你好1" + new Date());
        testMessageEntity.setData(userDTO);
        messageSender.send("sleep-test", testMessageEntity);
        Thread.sleep(10);
        userDTO.setTitle("世界你好2" + new Date());
        testMessageEntity.setData(userDTO);
        messageSender.send("sleep-test", testMessageEntity);
        Thread.sleep(10);
        userDTO.setTitle("世界你好3" + new Date());
        testMessageEntity.setData(userDTO);
        messageSender.send("sleep-test", testMessageEntity);

        messageSender.send("sleep-test1", testMessageEntity);

        Thread.sleep(10000 * 5);
    }

    /**
     * 让消费时长大于max.poll.interval.ms的时间，是否有重复消费情况
     */
    @SneakyThrows
    @Test
    public void manual() {
        UserDTO userDTO = new UserDTO();
        MessageEntity testMessageEntity = new MessageEntity();
        testMessageEntity.setData(userDTO);
        messageSender.send("sleep3", testMessageEntity);
        messageSender.send("sleep3", testMessageEntity);

        Thread.sleep(1000 * 30);
    }
}