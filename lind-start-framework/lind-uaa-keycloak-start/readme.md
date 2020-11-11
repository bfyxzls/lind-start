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