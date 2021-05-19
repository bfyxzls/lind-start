package com.lind.common.proxy.register;

import com.lind.common.proxy.demo.CarHandler;
import com.lind.common.proxy.demo.CarService;
import com.lind.common.proxy.demo.DefaultCarHandler;
import com.lind.common.proxy.demo.DefaultCarService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarConfig {
    @Bean("carService")
    @ConditionalOnMissingBean
    public CarService carService() {
        return new DefaultCarService();
    }

    @Bean("carHandler")
    @ConditionalOnMissingBean
    public CarHandler carHandler() {
        return new DefaultCarHandler();
    }
}
