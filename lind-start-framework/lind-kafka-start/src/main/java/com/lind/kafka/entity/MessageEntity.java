package com.lind.kafka.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 赵阳
 * @Description TODO
 * @date 2020/7/6 22:20
 **/
@Data
public class MessageEntity<T> implements Serializable {
    private static final long serialVersionUID = 3377490669064560046L;


    /**
     * 表名
     */
    private String tableName;

    /**
     * 消费者需要做的操作
     */
    private String operator;

    /**
     * 消息体
     */
    private T data;

    /**
     * 发送消息的用户名
     */
    private String sendUser;

    /**
     * 发送时间
     */
    private Date sendTime;


}
