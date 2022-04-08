package com.lind.common;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 类型的协变和逆变
 * 逆变与协变用来描述类型转换（type transformation）后的继承关系，其定义：如果A、B表示类型，f(⋅)表示类型转换，≤表示继承关系（比如，A≤B表示A是由B派生出来的子类）；
 * f(⋅)是逆变（contravariant）的，当A≤B时有f(B)≤f(A)成立；
 * f(⋅)是协变（covariant）的，当A≤B时有f(A)≤f(B)成立；
 * f(⋅) 是不变（invariant）的，当A≤B时上述两个式子均不成立，即f(A)与f(B)相互之间没有继承关系。
 */
@Slf4j
public class SuperClassTest {
    void print(Super super1) {
        log.info("{}", super1);
    }

    @Test
    public void superTest() {
        Sub sub = new Sub();
        sub.setName("zzl");
        sub.setEmail("zz@sina.com");
        print(sub);
    }

    @Data
    class Super {
        String name;
    }

    @Data
    @ToString(callSuper = true)
    class Sub extends Super {
        String email;
    }
}
