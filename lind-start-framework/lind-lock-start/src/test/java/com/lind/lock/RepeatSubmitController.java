package com.lind.lock;

import com.lind.lock.annotation.RepeatSubmit;
import org.springframework.stereotype.Component;

@Component
public class RepeatSubmitController {
    @RepeatSubmit()
    public String get() {
        return "success";
    }
}
