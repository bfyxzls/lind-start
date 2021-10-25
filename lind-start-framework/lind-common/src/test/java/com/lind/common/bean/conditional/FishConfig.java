package com.lind.common.bean.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FishConfig {
  @Bean
  @ConditionalOnClass(name = {"com.lind.common.bean.conditional.FishFood"})
  public Fish fish() {
    return new Fish();
  }

  @Bean
  @ConditionalOnBean(name = {"fishFoodBean"})
  public Fish2 fish2() {
    return new Fish2();
  }

  @Bean
  public FishFoodBean fishFoodBean() {
    return new FishFoodBean();
  }


  public class Fish {
    public void hello() {
      System.out.println("依赖类型FishFood");
    }
  }

  public class Fish2 {
    public void hello() {
      System.out.println("依赖一个bean");
    }
  }
}
