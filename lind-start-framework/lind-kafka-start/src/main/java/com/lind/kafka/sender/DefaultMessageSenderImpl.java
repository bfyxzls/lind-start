package com.lind.kafka.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lind.kafka.entity.MessageEntity;
import com.lind.kafka.handler.FailureHandler;
import com.lind.kafka.handler.SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
  * @Description TODO
 * @date 2020/7/7 12:45
 **/
@RequiredArgsConstructor
public class DefaultMessageSenderImpl implements MessageSender<MessageEntity> {

    private final SuccessHandler successHandler;

    private final FailureHandler failureHandler;

    private final KafkaTemplate<String, String> template;

    private final ObjectMapper objectMapper;

    @Override
    public void send(String topic, MessageEntity message) throws JsonProcessingException {
        sendMessage(topic, message, successHandler, failureHandler);
    }

    @Override
    public void send(String topic, MessageEntity message, SuccessHandler successHandler) throws JsonProcessingException {
        sendMessage(topic, message, successHandler, failureHandler);
    }

    @Override
    public void send(String topic, MessageEntity message, FailureHandler failureHandler) throws JsonProcessingException {
        sendMessage(topic, message, successHandler, failureHandler);
    }

    @Override
    public void send(String topic, MessageEntity message, SuccessHandler successHandler,
                     FailureHandler failureHandler) throws JsonProcessingException {
        sendMessage(topic, message, successHandler, failureHandler);
    }

    /**
     * 发送消息到kafka
     *
     * @param topic
     * @param message
     * @param successHandler
     * @param failureHandler
     */
    private void sendMessage(String topic, MessageEntity message, SuccessHandler successHandler,
                             FailureHandler failureHandler) throws JsonProcessingException {
        String s = objectMapper.writeValueAsString(message);
        ListenableFuture<SendResult<String, String>> send = template.send(topic, s);
        send.addCallback(
                new ListenableFutureCallback<SendResult<String, String>>() {

                    @Override
                    public void onFailure(Throwable ex) {

                        failureHandler.onFailure(topic, message, ex);
                    }

                    @Override
                    public void onSuccess(SendResult<String, String> result) {

                        successHandler.onSuccess(result);
                    }
                }
        );


    }


}
