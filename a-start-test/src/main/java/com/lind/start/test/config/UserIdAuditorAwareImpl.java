package com.lind.start.test.config;

import com.lind.redis.lock.template.UserIdAuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserIdAuditorAwareImpl implements UserIdAuditorAware {

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.empty();
	}

}
