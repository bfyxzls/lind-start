package com.lind.logger.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Method;

@Getter
@AllArgsConstructor
public class LogRootObject {

  /** * Target method */
  private final Method method;

  /** * Method parameters */
  private final Object[] args;

  /** * Type information for the target class */
  private final Class<? > targetClass;

}