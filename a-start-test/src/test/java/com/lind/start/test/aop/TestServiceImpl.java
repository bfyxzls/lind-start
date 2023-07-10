package com.lind.start.test.aop;

import com.lind.start.test.model.User;
import org.springframework.stereotype.Component;

@Component
public class TestServiceImpl implements TestService {

	@Override
	public void print(User user) {
		System.out.print(user.getUsername());
	}

}
