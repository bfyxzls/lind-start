# 事件处理
主要实现业务相关代码的解耦，使用了发布订阅的机制，订阅方需要实现ObjectEventListener接口，
事件相关对象需要实现ObjectEvent类型，使用方法使用ObjectEventService注入的实例即可完成事件
处理程序的注册，事件的发布也在这个对象里。
## event.AbstractEvent
抽象事件源，你的事件实体对象需要实现它
## EventBusListener
事件处理程序接口，你自定义的处理程序需要实现它，它里面的实体就是继承AbstractEvent的实体
## EventBusService
事件总线接口，定义了添加事件，触发事件的方法
## DefaultEventBusBusService
默认的事件总线实现，采用内存hash表来存储事件
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
