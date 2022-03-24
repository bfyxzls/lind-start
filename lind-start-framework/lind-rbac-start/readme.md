# rbac基于角色的权限控制
* 依赖认证组件lind-uaa-jwt-start
* 添加标准实体User,Role,Permission,UserRole,RolePermission
* 添加rest接口,UserController,RoleController,PermissionController
* 对UserDetailsService和ResourcePermissionService进行实现，默认通过mysql的方式