package com.lind.kafka;

import com.lind.kafka.anno.MqProducer;
import com.lind.kafka.anno.MqSend;
import com.lind.kafka.entity.MessageEntity;

@MqProducer
public interface MessageDataSend {
    @MqSend(topic = "ok_bobo")
    void sendDataMessage(MessageEntity<UserDTO> messageEntity);
}
