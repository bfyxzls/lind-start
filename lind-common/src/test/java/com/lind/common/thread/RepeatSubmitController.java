package com.lind.common.thread;

import com.lind.common.lock.RepeatSubmit;
import org.springframework.stereotype.Component;

@Component
public class RepeatSubmitController {
    @RepeatSubmit(key = "get")
    public String get() {
        return "success";
    }
}
