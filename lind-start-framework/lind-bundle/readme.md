# 说明
maven-shade-plugin主要功能
1. 将依赖的jar包打包到当前jar包（常规打包是不会将所依赖jar包打进来的）；
2. 对依赖的jar包进行重命名（用于类的隔离）；