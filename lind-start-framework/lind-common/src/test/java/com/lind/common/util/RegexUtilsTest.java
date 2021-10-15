package com.lind.common.util;

import com.lind.common.util.RegexUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class RegexUtilsTest {
  private static final Pattern DATA_FILE_RE = Pattern.compile("data\\.([0-9]+)"); // data.1

  @Test
  public void test() {
    // 将下面的国家中间的数字替换成空格
    String str = "China12345America678922England342343434Mexica";
    System.out.println(str.replaceAll("\\d+", " "));

    // 将下面的国家重叠的字符替换成 一个, 也就是去掉重复的分隔符
    str = "China|||||America::::::England&&&&&&&Mexica";
    System.out.println(str.replaceAll("(.)\\1+", "$1▲$1"));

    // 将字符串  "李阳 	王丽		李明    张俊 小雷" 的空格和tab 替换成 逗号
    str = "李阳 	王丽		李明    张俊 小雷";
    System.out.println(str.replaceAll("[ \\t]+", ","));

    str = "123aa-34345bb-234cc-00";
    System.out.println(str.replaceAll("(\\d{3,5})([a-z]{2})", "$1▲$2"));

    /**
     * 1、英文句点.符号：匹配单个任意字符。
     *
     *     表达式t.o 可以匹配：tno，t#o，teo等等。不可以匹配：tnno，to，Tno，t正o等。
     *
     * 2、中括号[]：只有方括号里面指定的字符才参与匹配，也只能匹配单个字符。
     *
     *     表达式：t[abcd]n 只可以匹配：tan，tbn，tcn，tdn。不可以匹配：thn，tabn，tn等。
     *
     * 3、| 符号。相当与“或”，可以匹配指定的字符，但是也只能选择其中一项进行匹配。
     *
     *     表达式：t(a|b|c|dd)n 只可以匹配：tan，tbn，tcn，tddn。不可以匹配taan，tn，tabcn等。
     */
    //^表示以什么开头,[]里面是可取的,\\s+表示后面可以有多个空格

    str = "g_d  ios  text";
    System.out.println(str.replaceAll("(g[_]d\\s+)(i[o|q|oos]s\\s+)([^text])", "$1\n$2\n$3"));

    str = "addd b c d";//第一位为a，第二位有a-z之前的3个字符，然后用_替换
    System.out.println(str.replaceAll("([a][a-z]{3}\\s)", "_"));

    str = "，男，";//第一位为[，。,；;,]里的一个字符，第二位男或女，最后是[，。,；;,]的一个字符
    System.out.println(str.replaceAll("([，。,；;,])([男 女][，。,；;,])", "$1▲$2"));

    str = "，家住北京大兴15房院";//第二位配置住和家住这两个词，然后以房院结尾，最后[^房院]是可选的
    System.out.println(str.replaceAll("([，。,；;,])(家?住[^房院])", "$1▲$2"));

    str = "，住北京大兴15房院";//第二位配置家住这个词，然后以房院结尾
    System.out.println(str.replaceAll("([，。,；;,])(家?住[^房院])", "$1▲$2"));

    str = "，家住北京";//第二位配置家住这个词，然后以房院结尾
    System.out.println(str.replaceAll("([，。,；;,])(家?住[^房院])", "$1▲$2"));
  }

  @Test
  public void hideOtherText() {
    String msg = "，1983年03月18日出生，";
    log.info(RegexUtils.hideOtherText(msg));
  }

  @Test
  public void replaceMobileHtml() {
    String mobile = RegexUtils.replaceMobileHtml("<p style='font-size:5px;'>136011712991</p>");
    log.info("mobile={}", mobile);
  }

  @Test
  public void abbreviate() {
    log.info(RegexUtils.abbreviate("hello world!", 10));
    log.info(RegexUtils.abbreviate("镇人民共和国", 10));

  }

  @Test
  public void containUpper() {
    Assert.assertTrue(RegexUtils.isContainUpper("abcDefg"));
  }

  @Test
  public void fileName() {
    Matcher matcher = DATA_FILE_RE.matcher("data.123");
    Assert.assertTrue(matcher.matches());
    Assert.assertTrue(DATA_FILE_RE.matcher("data.42").matches());
  }

  @Test
  public void max() {
    System.out.println(Math.max(1, 2));
    System.out.println(Math.max(2, 22));
    Long.parseLong("0001");
  }
  @Test
  public void group() {
    Matcher matcher = DATA_FILE_RE.matcher("data.001");
    matcher.matches();
    matcher.group(1);
  }
  @Test
  public void matcherGroup() {
    List<String> ids = Arrays.asList("data.1", "data.3", "data.2", "data.2");
    long maxFileId = -1L;
    for (String id : ids) {
      Matcher matcher = DATA_FILE_RE.matcher(id);
      matcher.matches();
      maxFileId = Math.max(Long.parseLong(matcher.group(1)), maxFileId); //group（0）就是指的整个串，group（1） 指的是第一个括号里的东西，group（2）指的第二个括号里的东西
      System.out.println("maxFileId=" + maxFileId);
    }

  }
}
