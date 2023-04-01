package com.lind.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lind.kafka.entity.MessageEntityAware;
import com.lind.kafka.handler.FailureHandler;
import com.lind.kafka.handler.SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.StringUtils;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * 默认消息发送者.
 **/
@RequiredArgsConstructor
public class DefaultMessageSender implements MessageSender<MessageEntityAware> {

	private final SuccessHandler successHandler;

	private final FailureHandler failureHandler;

	private final KafkaTemplate<String, String> template;

	private final ObjectMapper objectMapper;

	@Override
	public void send(String topic, MessageEntityAware message) throws JsonProcessingException {
		sendMessage(topic, message, successHandler, failureHandler);
	}

	@Override
	public void send(String topic, MessageEntityAware message, SuccessHandler successHandler)
			throws JsonProcessingException {
		sendMessage(topic, message, successHandler, failureHandler);
	}

	@Override
	public void send(String topic, MessageEntityAware message, FailureHandler failureHandler)
			throws JsonProcessingException {
		sendMessage(topic, message, successHandler, failureHandler);
	}

	@Override
	public void send(String topic, MessageEntityAware message, SuccessHandler successHandler,
			FailureHandler failureHandler) throws JsonProcessingException {
		sendMessage(topic, message, successHandler, failureHandler);
	}

	@Override
	public void send(String topic, String key, MessageEntityAware message) throws JsonProcessingException {
		sendMessage(topic, key, message, successHandler, failureHandler);
	}

	@Override
	public void send(String topic, String key, MessageEntityAware message, SuccessHandler successHandler)
			throws JsonProcessingException {
		sendMessage(topic, key, message, successHandler, failureHandler);
	}

	@Override
	public void send(String topic, String key, MessageEntityAware message, FailureHandler failureHandler)
			throws JsonProcessingException {
		sendMessage(topic, key, message, successHandler, failureHandler);
	}

	@Override
	public void send(String topic, String key, MessageEntityAware message, SuccessHandler successHandler,
			FailureHandler failureHandler) throws JsonProcessingException {
		sendMessage(topic, key, message, successHandler, failureHandler);
	}

	/**
	 * 发送消息到kafka
	 * @param topic
	 * @param message
	 * @param successHandler
	 * @param failureHandler
	 * @throws JsonProcessingException
	 */
	private void sendMessage(String topic, MessageEntityAware message, SuccessHandler successHandler,
			FailureHandler failureHandler) throws JsonProcessingException {
		sendMessage(topic, null, message, successHandler, failureHandler);
	}

	/**
	 * 发送消息到kafka
	 * @param topic
	 * @param message
	 * @param successHandler
	 * @param failureHandler
	 */
	private void sendMessage(String topic, String key, MessageEntityAware message, SuccessHandler successHandler,
			FailureHandler failureHandler) throws JsonProcessingException {
		String s = objectMapper.writeValueAsString(message);
		ListenableFuture<SendResult<String, String>> send;
		if (StringUtils.isEmpty(key)) {
			send = template.send(topic, s);
		}
		else {
			send = template.send(topic, key, s);
		}
		send.addCallback(new ListenableFutureCallbackWithTracing(new ListenableFutureCallback<SendResult<String, String>>() {

			@Override
			public void onFailure(Throwable ex) {
				if (failureHandler != null) {
					failureHandler.onFailure(topic, message, ex);
				}
			}

			@Override
			public void onSuccess(SendResult<String, String> result) {
				if (successHandler != null) {
					successHandler.onSuccess(result);
				}
			}
		}, MDC.getCopyOfContextMap()));

	}

}
