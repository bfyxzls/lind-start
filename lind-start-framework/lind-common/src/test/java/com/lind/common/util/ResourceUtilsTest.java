package com.lind.common.util;

import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;
import java.util.Properties;

public class ResourceUtilsTest {


  @SneakyThrows
  @Test
  public void test() {
    Locale current = LocaleContextHolder.getLocale();
    Properties properties = new LocaleMessageUtils(ResourceUtilsTest.class.getClassLoader()).getMessages("lind", current);
    System.out.println(properties.getProperty("hello"));
  }


}
