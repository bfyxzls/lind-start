package com.lind.start.test.controller;

import com.google.common.collect.ImmutableMap;
import com.lind.start.test.config.ConvertResponseProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MapJsonController {
    @GetMapping("map")
    @ConvertResponseProperty(fields = "id")
    public ResponseEntity map() {
        Map<String, Object> hashMap = new HashMap();
        hashMap.put("id", 1);
        hashMap.put("name", "lind");
        return ResponseEntity.ok(hashMap);
    }

    @GetMapping("maps")
    @ConvertResponseProperty(fields = "id", isArray = true)
    public ResponseEntity maps() {
        Map<String, Object> hashMap = new HashMap();
        hashMap.put("id", 1);
        hashMap.put("name", "lind");


        return ResponseEntity.ok(Arrays.asList(hashMap, ImmutableMap.of("id", 2, "name", "zzl")));
    }


}
