package com.lind.common.enums;

enum Test1 implements NameValueEnum<String> {
  T1("01", "String类型测试1"),
  T2("02", "String类型测试2");

  private String value;
  private String name;

  Test1(String value, String name) {
    this.value = value;
    this.name = name;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getValue() {
    return this.value;
  }
}
