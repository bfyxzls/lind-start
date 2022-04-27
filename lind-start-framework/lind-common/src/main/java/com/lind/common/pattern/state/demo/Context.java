package com.lind.common.pattern.state.demo;


public class Context {
    private State state;

    public Context(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    //对请求做处理
    public void handle() {
        state.doAction(this);
    }

}
