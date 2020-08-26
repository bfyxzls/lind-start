package com.lind.common;

import com.lind.common.util.IdMakerUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

@Slf4j
public class IdMakerTest {
    @Test
    public void mongodbId() throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            log.info("id={}", IdMakerUtils.generateId(i));
        }

    }

}
