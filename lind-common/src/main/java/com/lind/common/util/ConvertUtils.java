package com.lind.common.util;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class ConvertUtils {

  /**
   * 将整型转长整型.
   *
   * @param list .
   * @return
   */
  public static List<Long> convertLong(List<Integer> list) {
    List<Long> result = new ArrayList<>();
    for (Integer item : list) {
      result.add(Long.valueOf(item));
    }
    return result;
  }

  /**
   * 将字符转长整型.
   *
   * @param list .
   * @return
   */
  public static List<Long> convertLongFromString(List<String> list) {
    List<Long> result = new ArrayList<>();
    for (String item : list) {
      result.add(Long.parseLong(item));
    }
    return result;
  }

  /**
   * 从map里的object类型转long.
   *
   * @param list .
   * @return
   */
  public static List<Long> convertLongFromObject(Object list) {
    List<Long> result = new ArrayList<>();
    try {
      for (Integer item : (List<Integer>) list) {
        result.add(Long.valueOf(item));
      }
    } catch (Exception ex) {
      try {
        for (Long item : (List<Long>) list) {
          result.add(item);
        }
      } catch (Exception exStr) {
        for (String item : (List<String>) list) {
          result.add(Long.valueOf(item));
        }
      }
    }

    return result;
  }


  /**
   * 时间戳转日期格式.
   *
   * @param seconds .
   * @param format  .
   * @return
   */
  public static String timeStamp2Date(Long seconds, String format) {
    if (seconds == null || seconds.equals(0L)) {
      return "";
    }

    if (StringUtils.isEmpty(format)) {
      format = "yyyy-MM-dd'T'HH:mm:ss";
    }
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    return sdf.format(new Date(seconds));
  }

  /**
   * 四舍五入.
   *
   * @param value .
   * @param digit .
   * @return
   */
  public static String round(double value, int digit) {
    NumberFormat nf = NumberFormat.getNumberInstance();
    // 保留两位小数
    nf.setMaximumFractionDigits(digit);
    nf.setRoundingMode(RoundingMode.DOWN);
    return nf.format(value);

  }

  /**
   * 字符数组转Long数组.
   *
   * @param param .
   * @return
   */
  public static Long[] toLongArray(String[] param) {
    Long[] num = new Long[param.length];
    for (int idx = 0; idx < param.length; idx++) {
      num[idx] = Long.parseLong(param[idx]);
    }
    return num;
  }

  /**
   * 将对象转为map.
   *
   * @param object .
   * @return
   */
  public static Map<String, Object> toMap(Object object) {
    return JSON.parseObject(
        JSON.toJSONString(object),
        new TypeReference<Map<String, Object>>() {
        });
  }

  /**
   * 将对象转为object.
   *
   * @param object .
   * @return
   */
  public static <T> T toObject(Object object, Class<T> types) {
    return JSON.parseObject(
        JSON.toJSONString(object), types);
  }

}
