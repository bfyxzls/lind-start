package com.lind.common.pattern.state.order.core.impl;


import com.lind.common.pattern.state.order.constant.OrderStatusEnum;
import com.lind.common.pattern.state.order.core.AbstractOrderOperator;
import com.lind.common.pattern.state.order.core.annotation.OrderOperator;
import org.springframework.stereotype.Component;

/**
 * 创建订单操作状态流转
 **/
@Component
@OrderOperator
public class CreateOrderOperator extends AbstractOrderOperator {

    public CreateOrderOperator() {
        super.setStatus(1);
    }

    @Override
    public int handleEvent(int orderStatus, OrderStatusEnum orderStatusEnum) {
        if (orderStatus != OrderStatusEnum.CREATE_EVENT.status &&
                orderStatus != OrderStatusEnum.ORDER_CANCEL.status) {
            throw new IllegalArgumentException(String.format("create operation can't handle the status: %s", orderStatus));
        }
        System.out.println("进入创建订单状态扭转处理器...");
        switch (orderStatusEnum) {
            case CREATE_EVENT:
                return OrderStatusEnum.FORMAL_EVENT.status;
            case ORDER_CANCEL:
                return OrderStatusEnum.ORDER_CANCEL.status;
            default:
                return getStatus();
        }
    }
}
