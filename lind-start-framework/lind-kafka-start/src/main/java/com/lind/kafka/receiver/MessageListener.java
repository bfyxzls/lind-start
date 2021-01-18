package com.lind.kafka.receiver;

import com.lind.kafka.entity.MessageEntity;
import com.lind.kafka.handler.FailureHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;

import java.util.Optional;

/**
  * @Description 消息监听器
 * @date 2020/7/7 14:04
 **/
@Slf4j
@RequiredArgsConstructor
public class MessageListener<T extends MessageEntity> {

    private final MessageReceiver<T> messageReceiver;

    private final FailureHandler failureHandler;

    public void listen(ConsumerRecord<String, T> record, Acknowledgment ack) {

        try {
            Optional<ConsumerRecord<String, T>> kafkaMessage = Optional.ofNullable(record);
            if (kafkaMessage.isPresent()) {
                ConsumerRecord<String, T> message = kafkaMessage.get();
                String key = message.key();

                String topic = message.topic();
                long offset = message.offset();

                T value = message.value();
                boolean b = messageReceiver.messageReceive(value, failureHandler);
                if (b) {
                    ack.acknowledge();
                }
            }
        } catch (Exception e) {
            log.error("kafka消费失败:", e);
        }

    }

}
