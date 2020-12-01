# 认证中心
## 引用
```$xslt
      <dependency>
            <groupId>com.lind</groupId>
            <artifactId>lind-uaa-keycloak-start</artifactId>
            <version>1.0.0</version>
      </dependency>
```
## 配置中心
```$xslt
uaa:
  permitAll: /users
  callbackUri: http://localhost:9090/token/authorizationCodeLogin
keycloak:
  auth-server-url: http://192.168.119.130:8080/auth
  realm: demo
  resource: demoproduct
  public-client: true #如果设置为true，则适配器不会将客户端的凭据发送到Keycloak。 这是可选的。 默认值为false。
  principal-attribute: preferred_username
  use-resource-role-mappings: false #如果设置为true，则适配器将在令牌内部查找用户的应用程序级角色映射。 如果为false，它将查看用户角色映射的领域级别。 这是可选的。 默认值为false。
  cors: true
```
## 实例相关接口
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
    
    @Override
    public List<SourcePermission> getAll() {
        return Arrays.asList(
                new Permission("首页", "/index", "email"),
                new Permission("产品", "/products", "address")
        );
    }

}
```
# scope授权思路
通过实现JsonSerializer抽象类的serialize方法来进行指定类型的序列化，在序列化中对持有ScopeSet注解的字段进行解析，当没有对象的scope时，
对字段不进行渲染，从而保护了字段资源。
# 之前的尝试MappingJackson2HttpMessageConverter
之前使用`MappingJackson2HttpMessageConverter`的定义，将@ScopeSet进行检查，并对字段按着scope值进行输出，最后的结果是失败的，因为
`MappingJackson2HttpMessageConverter`只在程序启动时执行一次，将类对应的字段添加到它的字典之后，如果下次有相同的实体字段，就不会去再执行
MappingJackson2HttpMessageConverter了，而我们要求的是每次序列化时都需要进行重新的解析，以查询当前用户的scope是否有对应的权限。
# JsonSerializer的实现
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