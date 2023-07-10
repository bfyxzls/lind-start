package com.lind.start.test.conditionalonproperty;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * matchIfMissing=true表示没有配置run.type时,装载这个bean.
 */
@Component
@ConditionalOnProperty(prefix = "run", name = "type", havingValue = "dog", matchIfMissing = true)
public class DogRun implements Run {

	@Override
	public void doSth() {
		System.out.println("dog run");
	}

}
