package com.lind.logger;

import com.lind.logger.anno.LogRecordAnnotation;
import org.springframework.stereotype.Component;

@Component
public class UserService {
  @LogRecordAnnotation(detail = "修改了订单的配送员:${#user.getName()}")
  public void insert(User user) {
    System.out.println("insert user");
  }
}
