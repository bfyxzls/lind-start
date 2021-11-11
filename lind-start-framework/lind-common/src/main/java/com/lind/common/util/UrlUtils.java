package com.lind.common.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class UrlUtils {

  private static PathMatcher pathMatcher = new AntPathMatcher();

  /**
   * 从url中提取参数，并进行url编码.
   *
   * @param needValid
   * @return
   */
  public static String getUrlEncodeParams(String needValid) {
    if (needValid.indexOf("?") > 1) {
      String param = needValid.substring(needValid.indexOf("?"));
      String[] paramList = param.split("&");
      List<String> paramEncode = new ArrayList<>();
      for (String item : paramList) {
        String[] valAttr = item.split("=");
        if (valAttr.length > 1) {
          try {
            paramEncode.add(valAttr[0] + "=" + URLEncoder.encode(valAttr[1], "UTF-8"));
          } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
          }
        }
      }
      return needValid.substring(0, needValid.indexOf("?")) + String.join("&", paramEncode);
    }
    return needValid;
  }

  public static boolean match(String patternPath, String requestPath) {
    if (StringUtils.isEmpty(patternPath) || StringUtils.isEmpty(requestPath)) {
      return false;
    }
    return pathMatcher.match(patternPath, requestPath);
  }
}
