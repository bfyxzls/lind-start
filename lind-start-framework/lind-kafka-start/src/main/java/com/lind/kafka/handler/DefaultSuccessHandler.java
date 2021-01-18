package com.lind.kafka.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lind.kafka.entity.MessageEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;

/**
 * @Description TODO
 * @date 2020/7/7 11:30
 **/
@Slf4j
@RequiredArgsConstructor
public class DefaultSuccessHandler implements SuccessHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onSuccess(SendResult<String, String> result) {
        String topic = result.getProducerRecord().topic();
        try {
            MessageEntity value = objectMapper.readValue(result.getProducerRecord().value(), MessageEntity.class);

            RecordMetadata recordMetadata = result.getRecordMetadata();
            int partition = recordMetadata.partition();
            long offset = recordMetadata.offset();

            log.info("sent topic={} data={} with offset={} partition={}", topic, value,
                    offset, partition);
        } catch (JsonProcessingException e) {
            log.error("json反序列化失败", e);
        }

    }
}
