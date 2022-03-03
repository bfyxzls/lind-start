package bug;

import com.aliyun.openservices.log.Client;
import com.aliyun.openservices.log.common.LogItem;
import com.aliyun.openservices.log.exception.LogException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SLSDemo {
  public static void sendLog() throws LogException {
    String accessId = "LTAI4Fd1iaw81xnfnMWzTJrA";
    String accessKey = "sDVytI8BwcCfMTc5qLS9EPAHqny8ip";
    String host = "cn-beijing.log.aliyuncs.com";
    Client client = new Client(host, accessId, accessKey);

    String project = "proj-xtrace-a874a0b7338e2fd6105e9e15e1e158a8-cn-beijing";
    String logstore = "app-lind";


    int numLogGroup = 2;
/**
 * 向log service发送一个日志包，每个日志包中，有2行日志
 */
    for (int i = 0; i < numLogGroup; i++) {
      List<LogItem> logGroup = new ArrayList<LogItem>();
      LogItem logItem = new LogItem((int) (new Date().getTime() / 1000));
      logItem.PushBack("level", "info");
      logItem.PushBack("userid", String.valueOf(i));
      logItem.PushBack("message", "it's a test message");

      logGroup.add(logItem);

      LogItem logItem2 = new LogItem((int) (new Date().getTime() / 1000));
      logItem2.PushBack("level", "error");
      logItem2.PushBack("name", String.valueOf(i));
      logItem2.PushBack("message", "it's a test message");
      logGroup.add(logItem2);

      try {
        client.PutLogs(project, logstore, "hello", logGroup, "");
      } catch (LogException e) {
        System.out.println("error code :" + e.GetErrorCode());
        System.out.println("error message :" + e.GetErrorMessage());
        System.out.println("error requestId :" + e.GetRequestId());
        throw e;
      }

    }
  }
}
