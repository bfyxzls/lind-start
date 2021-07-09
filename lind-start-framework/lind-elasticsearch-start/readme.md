# 作用
对es的增删改进行封装，提取了实体基类`EsBaseEntity`，提供了公用的字段，id统一赋值，`createUser,createTime,updateUser,updateTime`通过拦截器统一进行赋值；提出了`EsAuditorAware`接口，使用者可以实现这个接口来返回当前登录的用户信息。
# 依赖引用
```
<dependency>
 <groupId>com.pkulaw</groupId>
 <artifactId>pkulaw-elasticsearch-start</artifactId>
 <version>1.0.0</version>
</dependency>
```
# 配置
使用`spring-boot-starter-data-elasticsearch`的标准配置即可
# 使用
开启es审计功能（为公用字段赋值）
```
@EnableEsAuditing
@SpringBootApplication
public class ElasticsearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchApplication.class, args);
    }
}
```
实现EsAuditorAware接口，返回当前用户Id
```
@Component
public class UserAuditorAware implements EsAuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("1"); //这块根据个别系统决定
    }
}
```
