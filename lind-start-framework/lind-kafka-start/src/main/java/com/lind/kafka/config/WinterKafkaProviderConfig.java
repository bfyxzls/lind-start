package com.lind.kafka.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lind.kafka.entity.MessageEntity;
import com.lind.kafka.handler.DefaultFailureHandler;
import com.lind.kafka.handler.DefaultSuccessHandler;
import com.lind.kafka.handler.FailureHandler;
import com.lind.kafka.handler.SuccessHandler;
import com.lind.kafka.sender.DefaultMessageSenderImpl;
import com.lind.kafka.sender.MessageSender;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 赵阳
 * @Description TODO
 * @date 2020/6/10 16:08
 **/

@Configuration
@RequiredArgsConstructor
public class WinterKafkaProviderConfig {

   @Primary
    @Bean("defaultFailureHandler")
    public FailureHandler failureHandler() {
        return new DefaultFailureHandler();
    }

    @Primary
    @Bean("defaultSuccessHandler")
    public SuccessHandler successHandler(ObjectMapper objectMapper) {
        return new DefaultSuccessHandler(objectMapper);
    }


    @Bean("messageSender")
    @ConditionalOnMissingBean
    public MessageSender<? extends MessageEntity> messageSender(SuccessHandler successHandler,FailureHandler failureHandler, KafkaTemplate<String,String> kafkaTemplate, ObjectMapper objectMapper) {

        return new DefaultMessageSenderImpl(successHandler, failureHandler, kafkaTemplate, objectMapper);
    }


    /**
     * 发送者配置
     *
     * @return
     */
    @Bean
    public ProducerFactory<?, ?> kafkaProducerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.119.131:9092");
        //重试次数
        props.put(ProducerConfig.RETRIES_CONFIG, 10);
        //批量发送数量，这里规定为1，保证发送的成功性
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 1);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 6000000);

        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);

    }

 }
