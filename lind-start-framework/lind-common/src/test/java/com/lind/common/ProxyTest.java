package com.lind.common;

import com.lind.common.proxy.anno.EnableCar;
import com.lind.common.proxy.demo.BusCarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableCar(basePackages = "com.lind.common.proxy")
public class ProxyTest {
    @Autowired
    BusCarService busCarService;

    @Test
    public void test() {
        busCarService.print("lind");
    }
}
