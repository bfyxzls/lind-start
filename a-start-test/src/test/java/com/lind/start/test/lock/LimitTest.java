package com.lind.start.test.lock;

import com.lind.redis.limit.config.LimitProperties;
import com.lind.redis.limit.aop.LimitRaterInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
@Slf4j
public class LimitTest {

	@Autowired
	LimitProperties limitProperties;

	@Autowired
	LimitRaterInterceptor limitRaterInterceptor;

	@Test
	public void getLimitPropertiesTest() {
		log.info(limitProperties.toString());
	}

}
