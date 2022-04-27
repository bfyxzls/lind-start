package com.lind.common.pattern;

import com.lind.common.pattern.state.demo.Context;
import com.lind.common.pattern.state.demo.StartState;
import com.lind.common.pattern.state.order.SpringBeanConfig;
import com.lind.common.pattern.state.order.constant.OrderStatusEnum;
import com.lind.common.pattern.state.order.core.OrderStateManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringBeanConfig.class)
public class StateTest {
    @Autowired
    private OrderStateManager orderStateManager;

    @Test
    public void statePatternDemo() {
        //状态模式，定义的状态的变更，类似于工作流.
        Context context = new Context(new StartState());
        context.handle();//开始
        context.handle();//审批
        context.handle();//结束
        context.handle();//结束
    }

    @Test
    public void createTest() {
        Assert.assertEquals(2, orderStateManager.handleEvent("123", OrderStatusEnum.CREATE_EVENT, 1));
    }
}
