# 作用
主要是对mybatis的封装，方便业务开发人员调用

# 依赖引用
```
<dependency>
    <groupId>com.pkulaw</groupId>
    <artifactId>pkulaw-mybatis-start</artifactId>
    <version>1.0.0</version>
</dependency>
```
# 配置
使用mybatis-plus-boot-starter的标准配置即可

# 实体类
统一继承base.BaseEntity，已经实现了审记功能，帮我们自己在添加和更新时填充的建立人，更新人，建立时间和更新时间等字段。

# 审计注解
维持着Jpa相同的注册，并扩展了一个当前登陆人部门的注解，我们在实体类中可以这样对它赋值 
```java
   /**
     * 建立人Id,需要实现AuditorAware接口.
     */
    @TableField("create_by")
    @CreatedBy
    private String createBy;

    /**
     * 建立时间.
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @TableField("create_time")
    @CreatedDate
    private Date createTime;

    /**
     * 更新人ID,需要实现AuditorAware接口.
     */
    @TableField("update_by")
    @LastModifiedBy
    private String updateBy;

    /**
     * 更新时间.
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @TableField("update_time")
    @LastModifiedDate
    private Date updateTime;
 
    /**
     * 建立部门.
     */
    @TableField("create_department_id")
    @CreatedDepartmentBy
    private String createDepartmentId;
```

# 对第三方应用开启审讯注解
```$xslt
@EnableMybatisAuditing
public class BasicTest {}
```

# mybatis统一的配置
对于一些软删除，分页等配置，我们在resources/META-INF/spring.factories里进行了自动注入，不需要开发人员去干预
```$xslt
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
  com.lind.mybatis.config.MybatisPlusConfig

```