package com.lind.common.bean.conditional.missingbean;

import org.springframework.stereotype.Component;

@Component
public class V1MissingBean implements MissingBean {

	@Override
	public void hello() {
		System.out.println("v1 MissingBean");
	}

}
