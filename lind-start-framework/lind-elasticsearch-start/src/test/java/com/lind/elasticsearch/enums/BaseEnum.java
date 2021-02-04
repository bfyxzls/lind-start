package com.pkulaw.test.enums;

public interface BaseEnum<E extends Enum<?>, T> {
    Integer getValue();

    String getDescription();
}