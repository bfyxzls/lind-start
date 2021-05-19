package com.lind.common.proxy.demo;

import com.lind.common.proxy.anno.CarAnno;
import com.lind.common.proxy.anno.CarServiceAnno;

@CarAnno
public interface BusCarService  {
    @CarServiceAnno
    void print(String msg);
}
