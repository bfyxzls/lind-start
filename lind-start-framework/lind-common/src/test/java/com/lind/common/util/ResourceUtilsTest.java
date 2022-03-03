package com.lind.common.util;

import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

  @Test
  public void readResourcesFile() throws IOException {
    File file = ResourceUtils.getFile("classpath:rsa_pcks8_pem.private");
    InputStream inputStream = new FileInputStream(file);
    InputStreamReader isr = new InputStreamReader(inputStream);
    BufferedReader br = new BufferedReader(isr);
    String line = null;
    while ((line = br.readLine()) != null) {
      System.out.println(line);
    }
  }
}
