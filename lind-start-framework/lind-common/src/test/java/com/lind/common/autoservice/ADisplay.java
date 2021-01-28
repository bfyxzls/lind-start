package com.lind.common.autoservice;

import com.google.auto.service.AutoService;

@AutoService(Display.class)
public class ADisplay implements Display {
    @Override
    public String display() {
        return "A Display";
    }
}