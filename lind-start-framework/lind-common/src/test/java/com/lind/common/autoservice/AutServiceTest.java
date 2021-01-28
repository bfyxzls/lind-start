package com.lind.common.autoservice;

import org.junit.Test;

public class AutServiceTest {
    @Test
    public void printService() {
        while (DisplayFactory.getSingleton().hasNextDisplay()) {
            System.out.println(
                    DisplayFactory.getSingleton().getDisplay().display());

        }

    }
}
