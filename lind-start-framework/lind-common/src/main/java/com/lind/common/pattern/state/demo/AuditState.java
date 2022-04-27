package com.lind.common.pattern.state.demo;


public class AuditState implements State {

    public void doAction(Context context) {
        System.out.println("审批");
        context.setState(new StopState());
    }
}
