package com.lind.common.pattern.chain.chain;

import com.lind.common.pattern.chain.ChainHandler;
import com.lind.common.pattern.chain.HandlerParameters;

public class RemoveService extends ChainHandler {
    @Override
    public void execute(HandlerParameters parameters) {
        System.out.println("删除");
    }
}
