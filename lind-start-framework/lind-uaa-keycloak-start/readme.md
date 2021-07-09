文章目录
* 对接KC统一认证中心-Springboot版
  * pom.xml添加引用
  * 相关配置
  * 实现相关接口
* scope授权思路
  * 之前的尝试MappingJackson2HttpMessageConverter
  * JsonSerializer的实现


# 对接KC统一认证中心-Springboot版
## pom.xml添加引用
```
<dependencyManagement>
    <dependencies>
          <dependency>
            <groupId>org.keycloak.bom</groupId>
            <artifactId>keycloak-adapter-bom</artifactId>
            <version>11.0.3</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
<dependency>
        <groupId>com.pkulaw</groupId>
        <artifactId>pkulaw-uaa-keycloak-start</artifactId>
        <version>1.0.0</version>
</dependency>
```
## 相关配置
```$xslt
uaa:
  permitAll: /users # 开放的地址
  callbackUri: http://localhost:9090/token/authorizationCodeLogin # 回调地址
keycloak:
  auth-server-url: http://192.168.119.130:8080/auth # kc服务器地址
  realm: demo # 域名称
  resource: demoproduct # 客户端（接入方）名称
  public-client: true # 如果设置为true，则适配器不会将客户端的凭据发送到Keycloak。 这是可选的。 默认值为false。
  principal-attribute: preferred_username
  use-resource-role-mappings: false # 如果设置为true，则适配器将在令牌内部查找用户的应用程序级角色映射。 如果为false，它将查看用户角色映射的领域级别。 这是可选的。 默认值为false。
  cors: true
```
## 实现相关接口
主要是指角色与你的权限的对应关系，如果由使用方去实现这两个接口，将用户对应的角色返回即可，你可以对你的实现方法采用缓存的设计及提高性能。
```$xslt
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements SourcePermission {

    /**
     * 菜单标题.
     */
    private String title;
    /**
     * 页面路径/资源链接url.
     */
    private String path;
    /**
     * 权限名称
     */
    private String auth;

}
@Service
public class PermissionServiceImpl implements PermissionService {
    
@Service
public class PermissionServiceImpl implements PermissionService {

    /**
     * 被保存的api资源.
     *
     * @return
     */
    @Override
    public List<ResourcePermission> getAll() {
        return Arrays.asList(
                new Permission("商品管理", "/products"),
                new Permission("用户管理", "/users"),
                new Permission("商品删除", "/data/sifa*"),
                new Permission("律所实务", "/data/lvsuo*"),
                new Permission("法学期刊", "/data/faxue*")
        );
    }

    @Override
    public List<ResourcePermission> getByRoleId(String roleKey) {
        if (roleKey.equals("商品管理员"))
            return Arrays.asList(
                    new Permission("商品管理", "/products"),
                    new Permission("商品添加", "/products/add"),
                    new Permission("商品删除", "/products/del")
            );
        else if (roleKey.equals("司法案例")) {
            return Arrays.asList(
                    new Permission("商品删除", "/data/sifa*")
            );
        } else if (roleKey.equals("律所实务")) {
            return Arrays.asList(
                    new Permission("律所实务", "/data/lvsuo*")
            );
        } else if (roleKey.equals("法学期刊")) {
            return Arrays.asList(
                    new Permission("法学期刊", "/data/faxue*")
            );
        }
        return null;
    }

}

```
# scope授权思路
通过实现JsonSerializer抽象类的serialize方法来进行指定类型的序列化，在序列化中对持有ScopeSet注解的字段进行解析，当没有对象的scope时，
对字段不进行渲染，从而保护了字段资源。

## 之前的尝试MappingJackson2HttpMessageConverter
之前使用`MappingJackson2HttpMessageConverter`的定义，将@ScopeSet进行检查，并对字段按着scope值进行输出，最后的结果是失败的，因为
`MappingJackson2HttpMessageConverter`只在程序启动时执行一次，将类对应的字段添加到它的字典之后，如果下次有相同的实体字段，就不会去再执行
MappingJackson2HttpMessageConverter了，而我们要求的是每次序列化时都需要进行重新的解析，以查询当前用户的scope是否有对应的权限。

## JsonSerializer的实现
接口在每次从服务端响应时都需要使用jackson的JsonSerializer功能，它有默认的实现方式，而我们可以重新去实现自己的方法，它在每次接口响应时都会被执行
ScopeSet

```$xslt
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ScopeSet {
    /**
     * 授权范围.
     *
     * @return
     */
    String value() default "";
}

```
ScopeJsonSerializer序列化
```$xslt
@Slf4j
public class ScopeJsonSerializer<T> extends JsonSerializer<T> {
    @SneakyThrows
    @Override
    public void serialize(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        //引用类型
        jsonGenerator.writeStartObject();
        for (Field field : t.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getAnnotation(ScopeSet.class) != null) {
                String value = field.getAnnotation(ScopeSet.class).value();
                if (Arrays.asList(SecurityUser.getScope()).contains(value)) {
                    jsonGenerator.writeObjectField(field.getName(), field.get(t));
                }
            } else {
                jsonGenerator.writeObjectField(field.getName(), field.get(t));
            }
        }
        jsonGenerator.writeEndObject();
    }
}
```
在DTO实体中使用，先在实体上声明注解`@JsonSerialize(using = ScopeJsonSerializer.class)`，之后在需要保护的字段上添加@ScopeSet注解即可