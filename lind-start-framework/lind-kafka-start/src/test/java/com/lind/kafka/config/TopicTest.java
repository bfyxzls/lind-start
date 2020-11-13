package com.lind.kafka.config;

import com.lind.kafka.ApplicationTests;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFutureCallback;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTests.class)
@EmbeddedKafka
public class TopicTest {

    @Autowired
    EmbeddedKafkaBroker broker;

    @Autowired
    KafkaTemplate<String, String> template;
    @Test
    public void sendTopic() {
        template.send("topic-kl", "hello").addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("failure");
            }

            @Override
            public void onSuccess(SendResult<String, String> objectObjectSendResult) {
                System.out.println("success" + objectObjectSendResult.toString());
            }
        });
    }
}
