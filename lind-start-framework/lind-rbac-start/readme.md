# rbac基于角色的权限控制
* 依赖认证组件lind-uaa-jwt-start
* 添加标准实体User,Role,Permission,UserRole,RolePermission
* 添加rest接口,UserController,RoleController,PermissionController
* 对UserDetailsService和ResourcePermissionService进行实现，默认通过mysql的方式，
  对于ResourcePermissionService来说，它需要连接mysql来读取权限信息，所以有自己的实现，而对于其它服务，可以直接使用默认的实现方式，通过redis来读取权限信息