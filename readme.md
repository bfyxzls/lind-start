# 项目介绍 
主要是由框架级目录和网关，授权，用例服务组成，用来介绍框架公用组件的定义和使用，start包的使用，nacos配置中心和注册中心的使用等。

# 项目进行了spotbugs检测的项目
1. lind-common
2. lind-elasticsearch-start
3. lind-hbase-start
4. lind-hbase2-start
5. lind-limit-start
6. lind-lock-start
7. lind-redis-start
8. lind-mybatis-start
9. lind-swagger-start

# 自定义start的方法
1. 建立新的工程，主要用来实现某个功能，如redis,mongodb的封装，方便调用
2. 添加配置类，有一些配置信息，可以在application.yml里去配置
3. 添加AutoConfigure类，主要定义这个工具的规则
4. 添加META-INF文件夹spring.factories文件，用来指定自动装配的AutoConfigure类

# start项目
以lind开头，以start结尾的项目，是其它项目的基础包
1. lind-common 公用工具包
2. lind-elasticsearch-start 封装了es的操作
3. lind-hbase-start 封装了hbase的操作
4. lind-mybatis-start 封装了mybatis plus的操作
5. lind-redis-start 封装了redis读写操作
6. lind-uaa-start 用户收取服务

# spring.factories
  springboot-start项目里，通过spring.factories进行自动注册，里面可以设置自动配置，自动监听，应用初始化，配置文件类型，环境变量等信息，
在我们封装扩展包时，可以使用这个文件帮使用者自动装配bean。
  如果不希望自动装配你的bean，可以为bean添加Enable开头的注解，类似于`EnableResourceServer`,`EnableAuthorizationServer`等。

# 项目里的公用包
1. 公用工具包一般是util，它是单数，事实上包的命名都是单数
2. util包里的类，一般用utils结尾，它是复数，这也是遵循国际上公用类库的一个标准，如com.google.guava和org.apache.commons.lang3里的类命名方式。

# 测试项目
1. a-start-gateway
2. a-start-oauth
3. a-start-test
4. a-start-test-depend &  a-start-test-depend-shade

# 问题和解决
 springboot启动后自动退出，可以添加tomcat包解决这个问题
 ```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-tomcat</artifactId>
    </dependency>
```docker-compose -f example/standalone-mysql.yaml up

# 项目报告
mvn site
> 注意：上面的命令速度非常慢，因为它要检测每个包是否在仓库里，可以添加`-Ddependency.locations.enabled=false`来关闭这个校验,这个主要针对spotsbug,checkstyle,PMD,test等进行文档报告的打针，运行时间比较长，单独执行某些任务也是可以的，
如下面只执行checkstyle,spotbugs,pmd: mvn compile -D maven.test.skip=true clean compile spotbugs:spotbugs  checkstyle:checkstyle 

mvn site -Ddependency.locations.enabled=false

```
<!-- 代码检查 -->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-checkstyle-plugin</artifactId>
    <version>3.1.1</version>
    <executions>
        <execution>
            <id>checkstyle</id>
            <phase>validate</phase>
            <goals>
                <goal>check</goal>
            </goals>
        </execution>
    </executions>
    <dependencies>
        <dependency>
            <groupId>com.puppycrawl.tools</groupId>
            <artifactId>checkstyle</artifactId>
            <version>8.29</version>
        </dependency>
    </dependencies>
    <configuration>
        <configLocation>/checkstyle.xml</configLocation>
        <encoding>UTF-8</encoding>
        <consoleOutput>true</consoleOutput>
        <failsOnError>true</failsOnError>
    </configuration>
</plugin>
<!-- 配置pmd对java源文件进行检查，检测出可以优化的代码 -->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-pmd-plugin</artifactId>
    <version>3.8</version>
</plugin>
<plugin>
    <groupId>com.github.spotbugs</groupId>
    <artifactId>spotbugs-maven-plugin</artifactId>
    <version>4.0.4</version>
    <dependencies>
        <!-- overwrite dependency on spotbugs if you want to specify the version of spotbugs -->
        <dependency>
            <groupId>com.github.spotbugs</groupId>
            <artifactId>spotbugs</artifactId>
            <version>4.1.2</version>
        </dependency>
    </dependencies>
</plugin>
<!-- 测试报告 -->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>2.22.2</version>
    <configuration>
        <skip>false</skip>
        <skipTests>false</skipTests><!-- false不跳过测试,true表示跳过测试项目-->
        <testFailureIgnore>true</testFailureIgnore>
    </configuration>
</plugin>
<!-- mvn site时使用 -->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-site-plugin</artifactId>
    <version>3.8.2</version>
</plugin>
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-project-info-reports-plugin</artifactId>
    <version>3.1.0</version>
</plugin>

```
# 测试报告
 mvn surefire-report:report
```$xslt
 <plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>2.22.2</version>
    <configuration>
        <skip>false</skip>
        <skipTests>false</skipTests><!-- false不跳过测试,true表示跳过测试项目-->
        <testFailureIgnore>true</testFailureIgnore>
    </configuration>
</plugin>
```