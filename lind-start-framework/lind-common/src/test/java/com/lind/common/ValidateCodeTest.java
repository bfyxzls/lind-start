package com.lind.common;

import com.lind.common.code.image.ImageCodeGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ValidateCodeTest {
    @Autowired
    ImageCodeGenerator imageCodeGenerator;
    @Test
    public void imageCode() {
        imageCodeGenerator.generate(null);
    }
}
