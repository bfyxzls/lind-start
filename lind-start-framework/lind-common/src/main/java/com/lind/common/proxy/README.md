# spring代理
* 对接口进行代理，不能有实现类，主要效仿在mybatis的实现
* 一般代理一个基接口，然后由不同的泛型子接口去继承它，从而代码这些子接口，完成统一的处理
# 代理的注册
* 使用springboot提供的注解直接注册
* 实现spring的ImportBeanDefinitionRegistrar,FactoryBean等接口完成注册