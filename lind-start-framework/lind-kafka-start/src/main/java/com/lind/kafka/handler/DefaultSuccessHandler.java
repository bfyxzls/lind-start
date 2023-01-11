package com.lind.kafka.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lind.kafka.entity.MessageEntityAware;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;

/**
 * 消息发送成功默认事件.
 */
@Slf4j
@RequiredArgsConstructor
public class DefaultSuccessHandler implements SuccessHandler {

	private final ObjectMapper objectMapper;

	@Override
	public void onSuccess(SendResult<String, String> result) {
		String topic = result.getProducerRecord().topic();
		try {
			MessageEntityAware value = objectMapper.readValue(result.getProducerRecord().value(),
					MessageEntityAware.class);

			RecordMetadata recordMetadata = result.getRecordMetadata();
			int partition = recordMetadata.partition();
			long offset = recordMetadata.offset();

			log.info("success sent topic={} data={} with offset={} partition={}", topic, value, offset, partition);
		}
		catch (JsonProcessingException e) {
			log.error("json反序列化失败", e);
		}

	}

}
