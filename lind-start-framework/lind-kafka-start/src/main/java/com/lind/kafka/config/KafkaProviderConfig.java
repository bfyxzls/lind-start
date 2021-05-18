package com.lind.kafka.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lind.kafka.entity.MessageEntity;
import com.lind.kafka.handler.DefaultFailureHandler;
import com.lind.kafka.handler.DefaultSuccessHandler;
import com.lind.kafka.handler.FailureHandler;
import com.lind.kafka.handler.SuccessHandler;
import com.lind.kafka.producer.DefaultMessageSender;
import com.lind.kafka.producer.MessageSender;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 自动装配管理.
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.kafka", value = "enabled", matchIfMissing = true, havingValue = "true")
@EnableConfigurationProperties(KafkaProperties.class)
@RequiredArgsConstructor
public class KafkaProviderConfig {

    @Autowired
    private KafkaProperties kafkaProperties;

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
    public MessageSender<? extends MessageEntity> messageSender(SuccessHandler successHandler, FailureHandler failureHandler, KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        return new DefaultMessageSender(successHandler, failureHandler, kafkaTemplate, objectMapper);
    }

    /**
     * 生产者自定义配置.
     *
     * @return
     */
    @Bean
    public DefaultKafkaProducerFactory<String, String> kafkaProducerFactory() {
        Map<String, Object> paras = new HashMap<>();
        paras.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        paras.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, kafkaProperties.getKeySerializer());
        paras.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, kafkaProperties.getValueSerializer());
        paras.put(ProducerConfig.ACKS_CONFIG, kafkaProperties.getAcks());
        return new DefaultKafkaProducerFactory<>(paras);
    }

    @Bean
    @ConditionalOnBean(DefaultKafkaProducerFactory.class)
    public KafkaTemplate<String, String> kafkaTemplate(DefaultKafkaProducerFactory<String, String> kafkaProducerFactory) {
        return new KafkaTemplate<>(kafkaProducerFactory, false);
    }


    public DefaultKafkaConsumerFactory<String, String> kafkaConsumerFactory() {
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        paraMap.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getGroupID());
        paraMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, kafkaProperties.getKeyDeserializer());
        paraMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, kafkaProperties.getValueDeserializer());
        paraMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, kafkaProperties.getEnableAutoCommit());

        return new DefaultKafkaConsumerFactory<>(paraMap);
    }


    /**
     * 消费者自定义配置.
     *
     * @return
     */
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(kafkaConsumerFactory());
        if (StringUtils.isEmpty(kafkaProperties.getConcurrency())) {
            factory.setConcurrency(30);
        } else {
            factory.setConcurrency(Integer.parseInt(kafkaProperties.getConcurrency()));
        }
        factory.getContainerProperties().setPollTimeout(1000);
        // 手动提交的时候需要设置AckMode为 MANUAL或MANUAL_IMMEDIATE
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.BATCH);

        return factory;
    }
}
