package com.lind.common.bean.conditional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class ConditionalOnClassTest {
  @Autowired
  FishConfig.Fish fish;

  @Autowired
  FishConfig.Fish2 fish2;

  /**
   * 依赖一个类
   */
  @Test
  public void dependOnClass() {
    fish.hello();
  }

  @Test
  public void dependOnBean() {
    fish2.hello();
  }
}
