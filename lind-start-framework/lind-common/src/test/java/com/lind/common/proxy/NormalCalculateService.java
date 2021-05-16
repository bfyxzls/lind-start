package com.lind.common.proxy;

import org.springframework.stereotype.Component;

/**
 * 接口的代理，类似于mybatis的实现
 */
@Component
public interface NormalCalculateService extends SpringDynamicProxyTest.CalculateService<SpringDynamicProxyTest.Peo> {

     void compose() ;

}