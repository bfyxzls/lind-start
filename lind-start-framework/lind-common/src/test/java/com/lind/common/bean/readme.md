* ApplicationContextAware
  *     void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
> 当一个类实现了这个接口之后，这个类就可以方便的获得ApplicationContext对象（spring上下文），Spring发现某个Bean实现了
> ApplicationContextAware接口，Spring容器会在创建该Bean之后，自动调用该Bean的setApplicationContext（参数）方法，
> 调用该方法时，会将容器本身ApplicationContext对象作为参数传递给该方法。

* InitializingBean
  * void afterPropertiesSet() throws Exception;
> 当一个类实现这个接口之后，Spring启动后，初始化Bean时，若该Bean实现InitialzingBean接口，会自动调用afterPropertiesSet()方法，
> 完成一些用户自定义的初始化操作。

* DisposableBean 
  * void destroy() throws Exception;
> 当一个类实现这个接口之后，允许在容器销毁该bean的时候获得一次回调。DisposableBean接口也只规定了一个方法：destroy