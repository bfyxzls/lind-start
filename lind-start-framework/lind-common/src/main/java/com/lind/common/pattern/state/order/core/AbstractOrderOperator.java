package com.lind.common.pattern.state.order.core;

import com.lind.common.pattern.state.order.constant.OrderStatusEnum;
import lombok.Data;

@Data
public abstract class AbstractOrderOperator {

    int status;

    public abstract int handleEvent(int orderStatus, OrderStatusEnum orderStatusEnum);
}
