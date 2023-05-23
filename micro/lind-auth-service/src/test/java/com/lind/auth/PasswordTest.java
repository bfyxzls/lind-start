package com.lind.auth;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author lind
 * @date 2023/5/23 8:13
 * @since 1.0.0
 */
public class PasswordTest {

	@Test
	public void test() {
		System.out.println(new BCryptPasswordEncoder().encode("111111"));
		System.out.println(new BCryptPasswordEncoder().encode("app"));

	}

}
