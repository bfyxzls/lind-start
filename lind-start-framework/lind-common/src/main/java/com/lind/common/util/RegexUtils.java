package com.lind.common.util;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * 常用正则封装.
 */
public class RegexUtils {
    /**
     * 隐藏出生日期、职位、年龄、工作、民族等信息.
     */
    public static String hideOtherText(String myText) {
        String rtnText = myText;
        rtnText = StringUtils.replaceAll(rtnText,
                "([，。,；;,][\\d一二三四五六七八九零０１２３４５６７８９]+年[\\d一二三四五六七八九零０１２３４５６７８９]+月[\\d一二三四五六七八九零０１２３４５６７８９]+日出?生)([，。,；;,])",
                "$1▲$2");
        rtnText = StringUtils.replaceAll(rtnText, "([，。,；;,])(家?住[^房院])", "$1▲$2");
        rtnText = StringUtils.replaceAll(rtnText, "([，。,；;,])((联系|注册)地)", "$1▲$2");
        rtnText = StringUtils.replaceAll(rtnText, "([，。,；;,])([男女][，。,；;,])", "$1▲$2");
        String rplText = "";
        rtnText = StringUtils.replaceAll(rtnText, "([，。,；;,][^，。,；;,▲]*▲[^。]*)", rplText);
        rtnText = rtnText.replaceAll("▲", "");
        return rtnText;
    }

    /**
     * 替换为手机识别的HTML，去掉样式及属性，保留回车.
     *
     * @param html html格式的手机号
     * @return 手机号
     */
    public static String replaceMobileHtml(String html) {
        if (html == null) {
            return "";
        }
        return html.replaceAll("<([a-z]+?)\\s+?.*?>", "<$1>");
    }

    /**
     * 替换掉HTML标签方法.
     */
    public static String replaceHtml(String html) {
        if (isBlank(html)) {
            return "";
        }
        String regEx = "<.+?>";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(html);
        String s = m.replaceAll("");
        return s;
    }

    /**
     * 缩略字符串（不区分中英文字符）.
     *
     * @param str    目标字符串
     * @param length 截取长度
     * @return 结果
     */
    public static String abbreviate(String str, int length) {
        if (str == null) {
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder();
            int currentLength = 0;
            for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
                currentLength += String.valueOf(c).getBytes("GBK").length;
                if (currentLength <= length - 3) {
                    sb.append(c);
                } else {
                    sb.append("...");
                    break;
                }
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
