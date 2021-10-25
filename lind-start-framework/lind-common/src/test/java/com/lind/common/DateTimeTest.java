package com.lind.common;

import cn.hutool.core.date.DateException;
import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;

import java.util.Date;

public class DateTimeTest {
  public static final String DATE_FORMAT_STR = "yyyy.MM.dd";

  public static String formatDate(String dateStr) {
    if (ObjectUtils.isEmpty(dateStr)) {
      return null;
    }
    try {
      return DateUtil.format(DateUtil.parse(dateStr), DATE_FORMAT_STR);
    } catch (DateException ex1) {
      ex1.getStackTrace();
      return null;
    }
  }

  @Test
  public void convert() throws JsonProcessingException {

    String jsonStr = "{\"createTime\":\"2021-09-29 08:17:24.123\"}";
    String jsonStr3 = "{\"createTime\":\"2021-09-29 08:17:24\"}";
    String jsonStr2 = "{\"createTime\":\"2021-09-29'T'08:17:24.123\"}";


    Json json = new ObjectMapper().readValue(jsonStr, Json.class);
    Json json3 = new ObjectMapper().readValue(jsonStr3, Json.class);
    Json json2 = new ObjectMapper().readValue(jsonStr2, Json.class);

    System.out.println(json.getCreateTime());
    System.out.println(json2.getCreateTime());
    System.out.println(json3.getCreateTime());

  }

  @Test
  public void dateTest() {
    System.out.println(
    formatDate("1998-09-01"));
  }


  @Data
  static class Json {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") //精确到秒  @JsonFormat(pattern="yyyy-MM-dd") //精确到天
    private Date createTime;
  }
}
