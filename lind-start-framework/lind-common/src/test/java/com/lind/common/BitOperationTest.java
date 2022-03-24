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
  public void binPower() {
    int a = 1 | 2 | 4 | 8;
    System.out.println(a);

  }

  /**
   * 分解2的N次方的和由哪些数组成.
   */
  @Test
  public void split2Power() {
    System.out.println(Math.pow(2,3));
    String numStr = Integer.toBinaryString(15);

    StringBuffer bf = new StringBuffer();
    for (int i = 0; i < numStr.length(); i++) {
      if (numStr.charAt(i) != '0') {
        bf.append(numStr.length() - 1 - i);
      }
    }
    int arr[] = new int[bf.length()];
    for (int i = 0; i < bf.length(); i++) {
      arr[i] = bf.charAt(i) - '0';
      System.out.println(Math.pow(2,arr[i]));
    }
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
