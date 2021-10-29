package com.lind.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class UrlUtils {

  /**
   * 从url中提取参数，并进行url编码.
   *
   * @param needValid
   * @return
   */
  public String getUrlEncodeParams(String needValid) {
    if (needValid.indexOf("?") > 1) {
      String url = needValid.substring(0, needValid.indexOf("?"));
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


}
