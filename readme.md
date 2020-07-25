# 项目介绍 
主要是由框架级目录和网关，授权，用例服务组成，用来介绍框架公用组件的定义和使用，start包的使用，nacos配置中心和注册中心的使用等。
# 自定义start的方法
1. 建立新的工程，主要用来实现某个功能，如redis,mongodb的封装，方便调用
2. 添加配置类，有一些配置信息，可以在application.yml里去配置
3. 添加AutoConfigure类，主要定义这个工具的规则
4. 添加META-INF文件夹spring.factories文件，用来指定自动装配的AutoConfigure类

# 测试项目
1. a-start-gateway
2. a-start-oauth
3. a-start-test

# 问题和解决
 springboot启动后自动退出，可以添加tomcat包解决这个问题
 ```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-tomcat</artifactId>
    </dependency>
```docker-compose -f example/standalone-mysql.yaml up