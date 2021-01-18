package com.lind.kafka.serialization;

import com.lind.kafka.entity.MessageEntity;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.util.SerializationUtils;

import java.util.Objects;

/**
  * @Description TODO
 * @date 2020/7/10 13:47
 **/
public class MessageEntitySerializer implements Serializer<MessageEntity> {


    @Override
    public byte[] serialize(String topic, MessageEntity data) {
        if (Objects.isNull(data)) {
            return null;
        }
        return SerializationUtils.serialize(data);
    }
}
