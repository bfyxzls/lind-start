# java动态代理模式
* 通过为接口添加注解，以及为接口中方法添加注解，来动态代理这个接口，去做一些事
* 而这些事，有一些固定的行为是统一实现的，动态的可变的，一般通过`函数式接口`或者回调方法来实现

# 需要几个对象
*  注解对象，你要代理的接口需要加上某个注解，以便让我们知道，这个接口需要被我们代理
*  ProviderProxy  是一个代码对象，这个对象会实现`InvocationHandler`，然后我们重写`invoke`为实现自己的逻辑
*　ProviderProxyFactoryBean 主要是生成一个FactoryBean对象，你给我什么，我就给你生成哪种类型的FactoryBean
*  ProviderBeanDefinitionRegistry 主要加载class文件，对实现了某个`注解`的接口进行注册
