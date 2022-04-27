package com.lind.common.pattern.state.order;


import com.lind.common.pattern.state.order.core.Initialization;
import com.lind.common.pattern.state.order.core.OrderStateManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@Configuration
public class SpringBeanConfig {

    @Bean
    public Initialization initialization() {
        return new Initialization();
    }

    @Bean
    public OrderStateManager orderStateManager() {
        return new OrderStateManager();
    }
}
