package com.lind.start.test.java8;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

public class ForeachTest {

	static final List<String> ARR = ImmutableList.of("1", "one", "2", "two");
	static Logger logger = LoggerFactory.getLogger(ForeachTest.class);

	@Test
	public void iteratorTest() {
		Iterator iterator = ARR.iterator();
		while (iterator.hasNext()) {
			logger.info(iterator.next().toString());
		}
	}

	@Test
	public void forEachTest() {
		ARR.forEach(logger::info);
	}

}
