package com.lind.lindmanager;

import com.lind.redis.lock.template.UserIdAuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author lind
 * @date 2022/8/17 17:17
 * @since 1.0.0
 */
@Component
public class UserIdAuditorAwareImpl implements UserIdAuditorAware {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.empty();
    }
}
