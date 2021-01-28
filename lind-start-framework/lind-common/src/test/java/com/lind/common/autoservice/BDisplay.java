package com.lind.common.autoservice;

import com.google.auto.service.AutoService;

// moduleb
@AutoService(Display.class)
public class BDisplay implements Display {
    @Override
    public String display() {
        return "B Display";
    }
}