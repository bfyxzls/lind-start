package com.lind.kafka.serialization;

import com.lind.kafka.entity.MessageEntity;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.util.SerializationUtils;

/**
  * @Description TODO
 * @date 2020/7/10 21:11
 **/
public class MessageEntityDeserializer implements Deserializer<MessageEntity> {
    @Override
    public MessageEntity deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        return (MessageEntity) SerializationUtils.deserialize(data);
    }
}
