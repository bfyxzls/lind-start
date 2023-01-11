package com.lind.common.pattern;

import com.lind.common.pattern.state.demo.Context;
import com.lind.common.pattern.state.demo.StartState;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class StateTest {

	@Test
	public void statePatternDemo() {
		// 状态模式，定义的状态的变更，类似于工作流.
		Context context = new Context(new StartState());
		context.handle();// 开始
		context.handle();// 审批
		context.handle();// 结束
		context.handle();// 结束
	}

}
