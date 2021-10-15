package com.lind.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.junit.Test;

import java.util.Date;

public class DateTimeTest {
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


  @Data
  static class Json {
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") //精确到秒  @JsonFormat(pattern="yyyy-MM-dd") //精确到天
    private Date createTime;
  }
}
