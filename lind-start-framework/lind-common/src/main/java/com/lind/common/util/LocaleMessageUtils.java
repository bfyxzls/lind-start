package com.lind.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 国际化消息配置.
 */
public class LocaleMessageUtils {
  ClassLoader classLoader;

  public LocaleMessageUtils(ClassLoader classLoader) {
    this.classLoader = classLoader;
  }

  /**
   * 得到消息.
   *
   * @param baseBundlename
   * @param locale
   * @return
   * @throws IOException
   */
  public Properties getMessages(String baseBundlename, Locale locale) throws IOException {
    if (locale == null) {
      return null;
    }
    Properties m = new Properties();
    URL url = classLoader.getResource(baseBundlename + "_" + locale.toString() + ".properties");
    if (url != null) {
      Charset encoding = PropertiesUtil.detectEncoding(url.openStream());
      try (Reader reader = new InputStreamReader(url.openStream(), encoding)) {
        m.load(reader);
      }
    }
    return m;
  }

  public static class PropertiesUtil {
    public static final Charset DEFAULT_ENCODING = Charset.forName("utf-8");
    public static final Pattern DETECT_ENCODING_PATTERN = Pattern.compile("^#\\s*encoding:\\s*([\\w.:-]+)",
        Pattern.CASE_INSENSITIVE);

    public static Charset detectEncoding(InputStream in) throws IOException {
      try (BufferedReader br = new BufferedReader(new InputStreamReader(in, DEFAULT_ENCODING))) {
        String firstLine = br.readLine();
        if (firstLine != null) {
          Matcher matcher = DETECT_ENCODING_PATTERN.matcher(firstLine);
          if (matcher.find()) {
            String encoding = matcher.group(1);
            if (Charset.isSupported(encoding)) {
              return Charset.forName(encoding);
            } else {
            }
          }
        }
      }
      return DEFAULT_ENCODING;
    }
  }
}
