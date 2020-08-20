package com.lind.common.thread;

import com.lind.common.lock.annotation.RepeatSubmit;
import org.springframework.stereotype.Component;

@Component
public class RepeatSubmitController {
    @RepeatSubmit()
    public String get() {
        return "success";
    }
}
