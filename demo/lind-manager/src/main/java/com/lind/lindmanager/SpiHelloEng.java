package com.lind.lindmanager;

import com.lind.pk.SpiHello;

/**
 * @author lind
 * @date 2023/2/7 17:52
 * @since 1.0.0
 */
public class SpiHelloEng implements SpiHello {
    @Override
    public void printHello() {
        System.out.println("hello world!");
    }
}
