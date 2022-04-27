package com.lind.common.pattern.state.order.model;

import lombok.Data;

/**
 * @author carpeng.gao@qunar.com
 * @date 2018/10/29 17:44
 **/
@Data
public class Order {

    private String orderId;

    private int orderStatus;
}
