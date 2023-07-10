package com.lind.proxy;

import com.lind.proxy.core.ProviderBeanDefinitionRegistry;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestProviderBeanDefinitionRegistry extends ProviderBeanDefinitionRegistry {

	@Override
	protected Class getAnnotation() {
		return AnnoProvider.class;
	}

}
