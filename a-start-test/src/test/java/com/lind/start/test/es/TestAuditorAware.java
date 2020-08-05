package com.lind.start.test.es;

import com.lind.elasticsearch.audit.EsAuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 得到当前操作人信息.
 */
@Component
public class TestAuditorAware implements EsAuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("1");
    }
}
