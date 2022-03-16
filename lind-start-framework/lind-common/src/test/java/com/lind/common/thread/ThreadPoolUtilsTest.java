package com.lind.common.thread;

import com.alibaba.fastjson.JSON;
import com.github.phantomthief.pool.KeyAffinityExecutor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolUtilsTest {
  static ExecutorService executorService = Executors.newFixedThreadPool(4);
  static KeyAffinityExecutor executor = KeyAffinityExecutor.newSerializingExecutor(8, 200, "MY-POOL");


  public static void executeByOldPool(List<Person> personList) {
    personList.stream().forEach(p -> executorService.execute(() -> {
      System.out.println(JSON.toJSONString(p));
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }));
  }

  public static void executeByAffinitydPool(List<Person> personList) {
    personList.stream().forEach(p -> executor.executeEx(p.getId(), () -> {
      System.out.println(JSON.toJSONString(p));
    }));
  }


  /**
   * 普通线程池无法保证有序性.
   *
   * @throws InterruptedException
   */
  @Test
  public void normalPool() throws InterruptedException {
    //1.创建列表
    List<Person> personList = new ArrayList<>();
    personList.add(Person.builder().id(1).data("1s").build());
    personList.add(Person.builder().id(2).data("2s").build());
    personList.add(Person.builder().id(1).data("11s").build());
    personList.add(Person.builder().id(3).data("3s").build());
    personList.add(Person.builder().id(1).data("111s").build());
    personList.add(Person.builder().id(2).data("22s").build());
    personList.add(Person.builder().id(3).data("33s").build());
    personList.add(Person.builder().id(1).data("1111s").build());

    //2.使用普通线程池执行
    executeByOldPool(personList);

    Thread.sleep(1000);
  }

  /**
   * Simple pool保存了有序性
   *
   * @throws InterruptedException
   */
  @Test
  public void phantomthiefSimplePool() throws InterruptedException {
    //1.创建列表
    List<Person> personList = new ArrayList<>();
    personList.add(Person.builder().id(1).data("1s").build());
    personList.add(Person.builder().id(2).data("2s").build());
    personList.add(Person.builder().id(1).data("11s").build());
    personList.add(Person.builder().id(3).data("3s").build());
    personList.add(Person.builder().id(1).data("111s").build());
    personList.add(Person.builder().id(2).data("22s").build());
    personList.add(Person.builder().id(3).data("33s").build());
    personList.add(Person.builder().id(1).data("1111s").build());

    executeByAffinitydPool(personList);

    Thread.sleep(1000);
  }


  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  static class Person {
    private Integer id;
    private String data;
  }
}
