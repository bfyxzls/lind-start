package com.lind.common.pattern.state.demo;

public class StopState implements State {

	public void doAction(Context context) {
		System.out.println("结束");
		context.setState(this);
	}

}
