package com.lind.lock;

import com.lind.lock.annotation.RepeatSubmit;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
public class RepeatSubmitController {
    @RepeatSubmit(expireTime = 1)
    @GetMapping("get")
    public String get() {
        return "success";
    }
}
