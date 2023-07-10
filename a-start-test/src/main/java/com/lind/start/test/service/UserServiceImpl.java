package com.lind.start.test.service;

import com.lind.start.test.CurrentThreadObj;
import org.springframework.stereotype.Component;

/**
 * @author lind
 * @date 2022/8/10 15:57
 * @since 1.0.0
 */
@Component
public class UserServiceImpl implements UserService {

	@Override
	public void getDetail(int id) {
		System.out.println(CurrentThreadObj.get("hello"));
	}

}
