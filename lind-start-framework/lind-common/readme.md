# 功能介绍
* aspect 拦截器相关，timer拦截器,记录代码运行时间;repeat拦截器,主要实现代码失败后的重试功能 
* encrypt 加密解密，hash,非对称，对称等
* com.lind.common.event 观察者模式，事件发布与订阅的实现，完成了对订阅者的自动初始化
* execption 异常管理，包含基类和control的异常拦截器
* jackson.convert 对springboot在http响应时，对jackson序列化的重写
* locale 国际化组件
* opt 通过时间（TOTP）或者数量（HOTP）进行校验，一般用在二步验证上面
* rest restful接口的返回值封装
* typetools 类型处理工具
* util 工具类
* zip 压缩工具

# 事件处理
主要实现业务相关代码的解耦，使用了发布订阅的机制，订阅方需要实现ObjectEventListener接口，
事件相关对象需要实现ObjectEvent类型，使用方法使用ObjectEventService注入的实例即可完成事件
处理程序的注册，事件的发布也在这个对象里。
## com.lind.common.event.AbstractEvent
抽象事件源，你的事件实体对象需要实现它
## EventBusListener
事件处理程序接口，你自定义的处理程序需要实现它，它里面的实体就是继承AbstractEvent的实体
## EventBusService
事件总线接口，定义了添加事件，触发事件的方法
## DefaultEventBusBusService
默认的事件总线实现，采用内存hash表来存储事件,init()方法添加了@PostConstruct注解，完成了事件的自动注册
# 加密
## util.RSAUtils
用于非对称加密，生成公钥和私钥，可以实例前端到后端请求的敏感字符加密，把公钥给前端，然后对数据加密，在后端通过私钥进行解密，当然，
也可以用来进行签名和验证签名，过程相反，后端通过私钥生成签名，前端通过公钥去验证签名。
## util.EncryptionUtils
加密工具类，主要提供Base64,MD5,DES等加密算法
## util.JasyptUtils
主要用在配置文件的密码加密上，因为你yml文件上，有很多密码，像mysql,redis,kafka等，我们不能使用明文密码，因为通过Jasypt对配置文件里的密码
字段进行加密。
配置文件如下
```$xslt
jasypt:
  encryptor:
    password: xboot
lind:
  password: ENC(xZK4Tols08HZ5PrQYVusVbuO516ClRYK91c+HsJT0FxNvrRp+iHLAil/YqUAkO6s) #通过JasyptUtil.encyptPwd生成的
```
## JarClassLoader 自定义类加载器加载外部jar
一、类加载过程
1. 加载    
加载指的是将类的class文件读入到内存，并为之创建一个java.lang.Class对象，也就是说，当程序中使用任何类时，系统都会为之建立一个java.lang.Class对象。
类的加载由类加载器完成，类加载器通常由JVM提供，这些类加载器也是前面所有程序运行的基础，JVM提供的这些类加载器通常被称为系统类加载器。除此之外，开发者可以通过继承ClassLoader基类来创建自己的类加载器。
通过使用不同的类加载器，可以从不同来源加载类的二进制数据，通常有如下几种来源。
从本地文件系统加载class文件，这是前面绝大部分示例程序的类加载方式。
从JAR包加载class文件，这种方式也是很常见的，前面介绍JDBC编程时用到的数据库驱动类就放在JAR文件中，JVM可以从JAR文件中直接加载该class文件。
通过网络加载class文件。
把一个Java源文件动态编译，并执行加载。
类加载器通常无须等到“首次使用”该类时才加载该类，Java虚拟机规范允许系统预先加载某些类。
2. 链接
    当类被加载之后，系统为之生成一个对应的Class对象，接着将会进入连接阶段，连接阶段负责把类的二进制数据合并到JRE中。
3. 初始化
初始化是为类的静态变量赋予正确的初始值，准备阶段和初始化阶段看似有点矛盾，其实是不矛盾的，如果类中有语句：private static int a = 10，它的执行过程是这样的，首先字节码文件被加载到内存后，先进行链接的验证这一步骤，验证通过后准备阶段，给a分配内存，因为变量a是static的，所以此时a等于int类型的默认初始值0，即a=0,然后到解析（后面在说），到初始化这一步骤时，才把a的真正的值10赋给a,此时a=10。