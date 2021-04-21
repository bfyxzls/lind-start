package com.lind.common;

import org.junit.Test;

/**
 * 取模.
 */
public class ModTest {

    @Test
    public void mod() {
        System.out.println("8 % 16=" + 8 % 16);
        //2的N次方数：二进制与运算等同于模运算
        System.out.println(8 & (16-1));

        System.out.println("7 % 5=" + 7 % 5);
        System.out.println(7 & (5 - 1));

        System.out.println("7 % 4=" + 7 % 4);
        System.out.println(7 & (4 - 1));
    }

}
