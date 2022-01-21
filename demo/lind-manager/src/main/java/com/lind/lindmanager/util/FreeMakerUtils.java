package com.lind.lindmanager.util;

import com.lind.common.util.FreeMarkerUtil;

import java.util.Map;

public class FreeMakerUtils {
  public static String outHtml(String ftl, String theme, Map<String, Object> model) {
    String html = new FreeMarkerUtil().processTemplate(model, ftl, theme);
    return html;
  }
}
