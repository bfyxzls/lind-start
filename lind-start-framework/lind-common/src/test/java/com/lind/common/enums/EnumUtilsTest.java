package com.lind.common.enums;

import org.junit.Assert;
import org.junit.Test;

public class EnumUtilsTest {
  @Test
  public void isExist() {
    Assert.assertTrue(EnumUtils.isExist(Test1.values(), "01"));
    Assert.assertFalse(EnumUtils.isExist(Test1.values(), "03"));
  }
}
