package com.lind.start.test.testconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AutoConfig {
    @Bean
    public AConfig aConfig() {
        return new AConfig("lind");
    }

    /**
     * 保证了AMapper只会注册一个.
     *
     * @param aConfig
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(AMapper.class)
    public AMapper aMapper1(AConfig aConfig) {
        return new AMapperImpl1(aConfig);
    }

    @Bean
    @ConditionalOnMissingBean(AService.class) //当bean不存在时才进行这个bean的注册
    public AService aService(AMapper aMapper) {
        return new AService(aMapper,"lind");
    }
}
