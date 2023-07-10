package com.lind.start.test.java8;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class StringTest {

	@Test
	public void builder() {
		StringBuilder str = new StringBuilder();
		str.append("hello").append("\n").append("world!");
		log.info(str.toString());
	}

	@Test
	public void replace() {
		String result = "abcd";
		result = result.replace("c", "3");
		log.info("replace={}", result);
	}

	@Test
	public void equal() {
		String a1 = "hello";
		String a2 = "hello";
		String a3 = new String("hello");
		log.info("a1 equals a2:{}", a1.equals(a2)); // true
		log.info("a1 = a2:{}", a1 == a2); // true

		log.info("a1 equals a3:{}", a1.equals(a3)); // true
		log.info("a1 = a3:{}", a1 == a3); // false
	}

	@Test
	public void concat() {
		String a1 = "hello";
		for (int i = 0; i < 5; i++) {
			a1 = a1.concat("hello");
		}
		log.info("a1={}", a1);

	}

}
