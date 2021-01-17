package com.lind.kafka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaTest {
    @Autowired
    MessageSender messageSender;

    @Test
    public void testReceivingKafkaEvents() throws InterruptedException {
        messageSender.send("ok");
        Thread.sleep(1000);
    }
}