package com.lind.kafka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class KafkaTest {
    @Autowired
    MessageSender sender;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @Test
    public void simple() throws InterruptedException {
        sender.send("hello world kafka!");
    }
}
