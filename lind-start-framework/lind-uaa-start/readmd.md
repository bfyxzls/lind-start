# 引用依赖包
```$xslt
 <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
            <version>${spring-boot-starter-security-version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.security.oauth</groupId>
            <artifactId>spring-security-oauth2</artifactId>
            <version>${spring-security-oauth2-version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-oauth2</artifactId>
            <version>${spring-cloud-starter-oauth2-version}</version>
        </dependency>
        <dependency>
            <groupId>com.lind</groupId>
            <artifactId>lind-mybatis-start</artifactId>
            <version>${lind-mybatis-start-version}</version>

        </dependency>
        <!-- redis -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
         <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
            <version>2.9.0</version>
        </dependency>
```
# 依赖指定的springboot和springcloud包
```$xslt
 <dependencyManagement>
        <dependencies>
            <!--spring cloud 版本-->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud-version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>${spring-boot-version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
 </dependencyManagement>
```
# 注意它们的版本
```$xslt
    <properties>
        <spring-boot-version>2.0.4.RELEASE</spring-boot-version>
        <spring-cloud-version>Finchley.RELEASE</spring-cloud-version>
        <spring-boot-starter-security-version>2.0.0.RELEASE</spring-boot-starter-security-version>
        <spring-security-oauth2-version>2.3.3.RELEASE</spring-security-oauth2-version>
        <spring-cloud-starter-oauth2-version>2.0.0.RELEASE</spring-cloud-starter-oauth2-version>
        <lind-mybatis-start-version>1.0.0</lind-mybatis-start-version>
    </properties>
```
# 客户端请求带上client_id和client_secret否则401
```$xslt
# url parameters
username:admin
password:123456
loginType:userLogin
saveLogin:true
grant_type:password
scope:app
client_id:system
client_secret:system
```
# 集成方式
直接通过lind-uaa-start包，然后建立对应的数据表，保存与uaa的实体对应即可，在application里配置对应的数据库链接串。