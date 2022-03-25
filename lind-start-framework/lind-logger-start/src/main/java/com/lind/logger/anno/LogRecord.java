package com.lind.logger.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface LogRecord {
  /**
   * 消息，支持spEL表达式.
   *
   * @return
   */
  String detail() default "";

  /**
   * 需要记录的更新对象ID.
   *
   * @return
   */
  String objectId() default "";
}
