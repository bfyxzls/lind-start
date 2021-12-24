# Java Agent是什么？
java agent本质上可以理解为一个jar包插件，这个jar包通过JVMTI（JVM Tool Interface）完成加载，最终借助JPLISAgent（Java Programming Language Instrumentation Services Agent）完成对目标代码的修改。-javaagent是java命令的一个参数，应用启动是我们可以利用这个参数javaagent指定一个jar包，去实现我们想要它做的一些事情。

# Java Agent能做什么？
1. 可以在加载class文件之前做拦截，对字节码做修改
1. 可以在运行期将已经加载的类的字节码做变更
1. 获取所有已经被加载过的类
1. 获取所有已经被初始化过了的类
1. 获取某个对象的大小
1. 将某个jar加入到bootstrapclasspath里作为高优先级被bootstrapClassloader加载
1. 将某个jar加入到classpath里供AppClassloard去加载
1. 设置某些native方法的前缀，主要在查找native方法的时候做规则匹配

比如我们熟知的链路追踪系统：skywalking，Alibaba开源的Java诊断工具：Arthas（阿尔萨斯）等都是使用的这个技术。

# 如何使用Java Agent?
1. 义一个jar包，这个jar包的MANIFEST.MF文件必须要包含Premain-Class项
1. remain-Class指定的类必须实现premain()方法，方法逻辑由用户自己定义
1. 使用参数-javaagent:jar包路径启动要代理的应用
```
public class PreMainAgent {
 
    public static void premain(String param, Instrumentation  instrumentation) {
        System.out.println("大家好，我是agent");
        System.out.println("agent param:" + param);
    }
}
```
pom文件添加Premain-Class
```        <plugins>
               <plugin>
                   <groupId>org.apache.maven.plugins</groupId>
                   <artifactId>maven-assembly-plugin</artifactId>
                   <configuration>
                       <appendAssemblyId>false</appendAssemblyId>
                       <descriptorRefs>
                           <descriptorRef>jar-with-dependencies</descriptorRef>
                       </descriptorRefs>
                       <archive>
                           <!--  自动添加META-INFO/MANIFEST.MF  -->
                           <manifest>
                               <addClasspath>true</addClasspath>
                           </manifest>
                           <manifestEntries>
                               <Premain-Class>com.cxd.agent.PreMainAgent</Premain-Class>
                               <Agent-Class>com.cxd.agent.PreMainAgent</Agent-Class>
                               <Can-Redefine-Classes>true</Can-Redefine-Classes>
                               <Can-Retransform-Classes>true</Can-Retransform-Classes>
                           </manifestEntries>
                       </archive>
                   </configuration>
                   <executions>
                       <execution>
                           <goals>
                               <goal>single</goal>
                           </goals>
                           <phase>package</phase>
                       </execution>
                   </executions>
               </plugin>
           </plugins>
       </build>
  ```