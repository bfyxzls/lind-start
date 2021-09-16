package com.lind.common;

import org.junit.Test;

/**
 * 位运算.
 */
public class BitOperationTest {
    @Test
    public void or() {
        boolean a = true;
        boolean b = false;
        a |= b;
        System.out.println("a=" + a);

        boolean c = false;
        boolean d = true;
        c |= d;
        System.out.println("c=" + c);

    }

    @Test
    public void flag() {
        int a = 1;
        int b = a << 2 << 2;
        System.out.println("b=" + b);

        // 包含
        int c = b & 4;
        System.out.println("b contaions 4:" + c);

        // 相加
        int d = 4;
        int e = b | d;
        System.out.println("b+d=" + e);

        int c2 = e & 4;
        System.out.println("e contaions 4:" + c2);

        // 相减
        int f = b & (~d);
        System.out.println("b-d=" + f);

    }
}
