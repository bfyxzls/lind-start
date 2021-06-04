package com.lind.start.test.controller;

import com.lind.common.aspect.logger.RunTime;
import com.lind.start.test.dto.Info;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RequestMapping("cache-test")
@RestController
public class CacheController {
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
