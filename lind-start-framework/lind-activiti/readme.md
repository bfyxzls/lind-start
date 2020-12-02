# lind-activiti
> 注意：如果你项目中使用了mybatis，需要注意它与lind-activiti里的mybatis的版本，当出现冲突后，可以把lind-activiti里的排除
## 文档
http://localhost:9090/doc.html
## 安装
```$xslt
      <dependency>
            <groupId>com.lind</groupId>
            <artifactId>lind-activiti</artifactId>
            <version>1.0.0</version>
       </dependency>
```
## 使用
```$xslt
@SpringBootApplication(exclude = {org.activiti.spring.boot.SecurityAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,})

public class FilingApplication {
    public static void main(String[] args) {
        SpringApplication.run(FilingApplication.class, args);
    }
}
```
## 配置
```
spring:
  activiti:
    check-process-definitions: false
    font:
      activityFontName: 宋体
      labelFontName: 宋体
```
## 模型列表
地址：/view/model/list