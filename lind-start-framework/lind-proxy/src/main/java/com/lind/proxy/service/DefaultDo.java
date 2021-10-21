package com.lind.proxy.service;

import org.springframework.stereotype.Component;

@Component
public class DefaultDo implements Do {
  @Override
  public void send(String message) {
    System.out.println("default do." + message);
  }
}
