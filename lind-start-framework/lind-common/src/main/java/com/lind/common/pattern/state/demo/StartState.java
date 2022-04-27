package com.lind.common.pattern.state.demo;


public class StartState implements State {

    public void doAction(Context context) {
        System.out.println("开始");
        context.setState(new AuditState());
    }

}
