package com.lind.common.pattern.lockstatusmachine;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lind
 * @date 2023/2/28 17:48
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StatemachineTest {

	@Autowired
	private StateMachine<TurnstileStates, TurnstileEvents> stateMachine;

	@Test
	public void test() {
		stateMachine.start();
		System.out.println("--- coin ---");
		stateMachine.sendEvent(TurnstileEvents.COIN);
		System.out.println("--- coin ---");
		stateMachine.sendEvent(TurnstileEvents.COIN);
		System.out.println("--- push ---");
		stateMachine.sendEvent(TurnstileEvents.PUSH);
		System.out.println("--- push ---");
		stateMachine.sendEvent(TurnstileEvents.PUSH);
		stateMachine.stop();
	}

}
