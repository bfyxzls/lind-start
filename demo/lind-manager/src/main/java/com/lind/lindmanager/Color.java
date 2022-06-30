package com.lind.lindmanager;

import com.lind.common.enums.NameValueEnum;

public enum Color implements NameValueEnum {
    red(1,"红"),  blue(2,"绿");

    Color(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    private Integer value;
    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
