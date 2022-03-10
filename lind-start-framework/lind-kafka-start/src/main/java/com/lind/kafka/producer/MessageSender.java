package com.lind.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lind.kafka.entity.MessageEntityAware;
import com.lind.kafka.handler.FailureHandler;
import com.lind.kafka.handler.SuccessHandler;

/**
 * 发送者.
 **/
public interface MessageSender<T extends MessageEntityAware> {

  /**
   * 发布消息.
   *
   * @param topic   主题
   * @param message 消息
   * @throws JsonProcessingException
   */
  void send(String topic, T message) throws JsonProcessingException;

  /**
   * 发布消息.
   *
   * @param topic          主题
   * @param message        消息
   * @param successHandler 发送成功后的事件处理
   * @throws JsonProcessingException
   */
  void send(String topic, T message, SuccessHandler successHandler) throws JsonProcessingException;

  /**
   * 发布消息.
   *
   * @param topic          主题
   * @param message        消息
   * @param failureHandler 发送失败后的事件处理
   * @throws JsonProcessingException
   */
  void send(String topic, T message, FailureHandler failureHandler) throws JsonProcessingException;

  /**
   * 发布消息.
   *
   * @param topic          主题
   * @param message        消息
   * @param successHandler 发送成功后的事件处理
   * @param failureHandler 发送失败后的事件处理
   * @throws JsonProcessingException
   */
  void send(String topic, T message, SuccessHandler successHandler, FailureHandler failureHandler) throws JsonProcessingException;

  /**
   * 发布消息.
   *
   * @param topic   主题
   * @param key     消息的key,相同的key发到相同的partition
   * @param message 消息
   * @throws JsonProcessingException
   */
  void send(String topic, String key, T message) throws JsonProcessingException;

  /**
   * 发布消息.
   *
   * @param topic          主题
   * @param key            消息的key,相同的key发到相同的partition
   * @param message        消息
   * @param successHandler 发送成功后的事件处理
   * @throws JsonProcessingException
   */
  void send(String topic, String key, T message, SuccessHandler successHandler) throws JsonProcessingException;

  /**
   * 发布消息.
   *
   * @param topic          主题
   * @param key            消息的key,相同的key发到相同的partition
   * @param message        消息
   * @param failureHandler 发送失败后的事件处理
   * @throws JsonProcessingException
   */
  void send(String topic, String key, T message, FailureHandler failureHandler) throws JsonProcessingException;

  /**
   * 发布消息.
   *
   * @param topic          主题
   * @param key            消息的key,相同的key发到相同的partition
   * @param message        消息
   * @param successHandler 发送成功后的事件处理
   * @param failureHandler 发送失败后的事件处理
   * @throws JsonProcessingException
   */
  void send(String topic, String key, T message, SuccessHandler successHandler, FailureHandler failureHandler) throws JsonProcessingException;
}
