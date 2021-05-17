package com.lind.common.proxy;

import com.lind.common.proxy.anno.MessageProvider;
import com.lind.common.proxy.anno.MessageSend;

/**
 * 接口的代理，类似于mybatis的实现
 */
@MessageProvider
public interface DemoService {

     @MessageSend(topic="lind")
     void send(MessageEntity<Object> messageEntity) ;

}