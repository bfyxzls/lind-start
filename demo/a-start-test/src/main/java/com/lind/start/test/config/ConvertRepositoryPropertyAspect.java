package com.lind.start.test.config;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * 类型转换.
 */
@Slf4j
@Aspect
@Component
public class ConvertRepositoryPropertyAspect {


    @Around("@annotation(convertResponseProperty)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, ConvertResponseProperty convertResponseProperty) throws Throwable {
        Object o = proceedingJoinPoint.proceed();
        String result = JSONObject.toJSONString(o);
        JSONObject obj = JSONObject.parseObject(result);
        if (convertResponseProperty.isArray()) {
            JSONArray jsonArray = obj.getJSONArray("body");
            for (int i = 0; i < jsonArray.size(); i++) {
                String objString = JSONObject.toJSONString(jsonArray.get(i));
                JSONObject jsonObject = JSONObject.parseObject(objString);
                if (jsonObject.containsKey(convertResponseProperty.fields())) {
                    jsonObject.put(convertResponseProperty.fields(), String.valueOf(jsonObject.get(convertResponseProperty.fields())));
                }
                jsonArray.set(i, jsonObject);
            }
            return ResponseEntity.ok(jsonArray);
        } else {
            JSONObject jsonObject = obj.getJSONObject("body");
            if (jsonObject.containsKey(convertResponseProperty.fields())) {
                jsonObject.put(convertResponseProperty.fields(), String.valueOf(jsonObject.get(convertResponseProperty.fields())));
            }
            return ResponseEntity.ok(jsonObject);
        }
    }

}
