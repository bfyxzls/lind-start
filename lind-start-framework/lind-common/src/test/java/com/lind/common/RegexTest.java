package com.lind.common;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class RegexTest {

	private static final Logger logger = LoggerFactory.getLogger(RegexTest.class);

	private static final Pattern DOT = Pattern.compile("\\.");

	private static final Pattern AUTHORIZATION_PATTERN = Pattern.compile("^Bearer (?<token>[a-zA-Z0-9-:._~+/]+=*)$",
			Pattern.CASE_INSENSITIVE);

	@Test
	public void authCode() {
		String[] parsed = DOT.split("abc.123.400.aa", 3);// 限制3位，这个数组被分为abc,123,400.aa，最后一位.就不进行拆分了
		logger.info("{}", parsed);
	}

	@Test
	public void bearer() {
		String token = "Bearer eyJhbGciOiJIUzI1NiIsInR5c+";
		Matcher matcher = AUTHORIZATION_PATTERN.matcher(token);
		if (!matcher.matches()) {
			log.error("Bearer token is malformed");
		}
		else {
			log.info("Bearer token={}", matcher.group("token"));
		}
	}

}
