package com.lind.start.test.testconfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ConfigTest {

	@Autowired
	AService aService;

	@Test
	public void test() {
		aService.print();
	}

}
