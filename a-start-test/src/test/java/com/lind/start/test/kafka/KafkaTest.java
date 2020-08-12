package com.lind.start.test.kafka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class KafkaTest {
    @Autowired
    Sender sender;

    @Test
    public void simple() throws InterruptedException {
        sender.send("hello world kafka!");
        Thread.sleep(5000);
    }
}
