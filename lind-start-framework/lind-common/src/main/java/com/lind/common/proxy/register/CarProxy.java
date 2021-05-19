package com.lind.common.proxy.register;

import com.lind.common.proxy.anno.CarServiceAnno;
import com.lind.common.proxy.demo.CarHandler;
import com.lind.common.proxy.demo.CarService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
@Data
public class CarProxy implements InvocationHandler {
    private BeanFactory applicationContext;
    private CarService carService;

    public CarProxy(BeanFactory applicationContext) {
        this.applicationContext = applicationContext;
        carService = applicationContext.getBean(CarService.class);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (args.length != 1) {
            throw new IllegalArgumentException("方法只能有一个参数");
        }
        Object arg = args[0];
        log.info("invoke arg={}", arg);
        if (method.isAnnotationPresent(CarServiceAnno.class)) {
            CarServiceAnno annotation = method.getAnnotation(CarServiceAnno.class);

            Class<? extends CarHandler> sccessHandlerType = annotation.onAfter();
            CarHandler successHandler = applicationContext.getBean(CarHandler.class);
            carService.doing("ok", successHandler);
        }
        return null;
    }


}
