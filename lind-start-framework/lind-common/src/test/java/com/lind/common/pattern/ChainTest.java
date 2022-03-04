package com.lind.common.pattern;

import com.lind.common.pattern.chain.HandlerParameters;
import com.lind.common.pattern.chain.WorkFlow;
import com.lind.common.pattern.chain.bll.WorkFlow1;
import com.lind.common.pattern.chain.bll.WorkFlow2;
import org.junit.Test;

/**
 * 在责任链模式里：很多对象由每一个对象对其下家的引用而连接起来形成一条链.
 */
public class ChainTest {
    @Test
    public void chainFlow1() {
        WorkFlow workFlow = new WorkFlow1();
        workFlow.ProcessRequest(new HandlerParameters("doing", "test"));
    }
    @Test
    public void chainFlow2() {
        WorkFlow workFlow = new WorkFlow2();
        workFlow.ProcessRequest(new HandlerParameters("doing", "test"));
    }

}
