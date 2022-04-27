package com.lind.common.pattern.state.order.core.order.impl;

import com.lind.common.pattern.state.order.core.AbstractOrderProcessor;
import com.lind.common.pattern.state.order.core.annotation.OrderProcessor;
import org.springframework.stereotype.Component;

@Component
@OrderProcessor
public class CreateOrderProcessor extends AbstractOrderProcessor {

    public CreateOrderProcessor() {
        super.setStatus(1);
    }

    @Override
    public boolean process(String orderId, Object... params) {
        // TODO 创建/取消订单对应的数据库修改，mq发送等操作，可以在此处process方法中完成
        System.out.println("进入创建订单后处理器...");
        return true;
    }
}
