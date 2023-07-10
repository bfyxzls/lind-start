package com.lind.start.test.conditionalonproperty;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "run", name = "type", havingValue = "cat")
public class CatRun implements Run {

	@Override
	public void doSth() {
		System.out.println("cat run");
	}

}
