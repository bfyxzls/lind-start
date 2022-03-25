package com.lind.logger;

import com.lind.logger.anno.LogRecord;
import com.lind.logger.entity.LogRootObject;
import org.springframework.stereotype.Component;

@Component
public class UserService {
  @LogRecord(detail = "修改了订单的配送员:{#user.getName()}")
  public void insert(User user) {
    LogRootObject.setObjId("1");
    System.out.println("insert user");
  }

  public void update(User user) {
    System.out.println("update user");
  }
}
