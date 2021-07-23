package com.lind.lindmanager.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * 通用访问匹配页面跳转
 *
 * @author Wang926454
 * @date 2019/1/24 19:27
 */
@RestController
@RequestMapping("cache")
public class CacheController {

    @PostMapping("add")
    @CacheEvict(value = "tree", key = "#p0.tableName")
    public ResponseEntity add(@RequestBody ColumnInfoParam columnInfoParam) {
        return ResponseEntity.ok("ok");
    }

    @GetMapping("add-test/{tableName}")
    @CacheEvict(value = "tree", key = "#p0")
    public ResponseEntity addTest(@PathVariable("tableName") String tableName) {
        return ResponseEntity.ok("ok");
    }

    @GetMapping("list/{key}")
    @Cacheable(value = "tree",key = "#p0")
    public ResponseEntity<List<ColumnInfoParam>> listColumnInfo(@PathVariable("key") String key) {
        return ResponseEntity.ok(Arrays.asList(new ColumnInfoParam("zzl")));
    }

    @GetMapping("list")
    @Cacheable(value = "tree")
    public ResponseEntity<List<ColumnInfoParam>> listColumnInfo() {
        return ResponseEntity.ok(Arrays.asList(new ColumnInfoParam("zzl")));
    }
}
