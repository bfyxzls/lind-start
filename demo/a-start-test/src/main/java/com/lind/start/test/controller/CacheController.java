package com.lind.start.test.controller;

import com.lind.common.aspect.timer.RunTime;
import com.lind.start.test.dto.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * redis cache default data
 * 默认是二进制，如果希望JSON格式，需要添加自定义序列化bean,RedisCacheConfiguration
 * [
 * "com.lind.start.test.dto.Info",
 * {
 * "title": "zzl",
 * "msg": "lind",
 * "createDate": [
 * "java.util.Date",
 * "2022-02-17T07:03:38.926+00:00"
 * ]
 * }
 * ]
 */
@RequestMapping("cache-test")
@EnableCaching
@RestController
public class CacheController {
  @Autowired
  RedisTemplate redisTemplate;

  @GetMapping("multi")
  public Info multi() {
    redisTemplate.opsForValue().set("hello", new Info("zzl", "lind", new Date()));
    return new Info("zzl", "lind", new Date());
  }

  /**
   * 不参数的
   * redis key list::SimpleKey []
   *
   * @return
   */
  @RunTime
  @Cacheable(value = "list", key = "")
  @GetMapping("list")
  public Info list() {
    return new Info("zzl", "lind", new Date());
  }

  /**
   * 带参数的，支持实体类型
   * redis key list::name,instance of list::zhansan
   *
   * @param name
   * @return
   */
  @GetMapping("detail")
  @Cacheable(value = "list", key = "#p0")
  public Info listOne(@RequestParam String name) {
    return new Info("zzl", "lind", new Date());
  }

  /**
   * del redis key for list::SimpleKey []
   *
   * @return
   */
  @GetMapping("del")
  @CacheEvict(value = "list")
  public String delAll() {
    return "ok";
  }

  /**
   * del redis key for list::name
   *
   * @param name
   * @return
   */
  @GetMapping("del/{name}")
  @CacheEvict(value = "list", key = "#p0")
  public String del(@PathVariable String name) {
    return "ok";
  }
}
