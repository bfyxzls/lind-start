package com.lind.http2;

import com.github.benmanes.caffeine.cache.CacheWriter;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.RemovalCause;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
public class CacheConfig {
    @Bean
    public LoadingCache<String, Object> expiryCache() {
        return Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(1000)
                //缓存写入/删除监控
                .writer(new CacheWriter<Object, Object>() {
                    //此方法是同步阻塞的
                    @Override
                    public void write(Object key, Object value) {
                        log.info("--缓存写入--:key=[{}]  value=[{}]", key, value);
                    }

                    @Override
                    public void delete(Object key, Object value, RemovalCause cause) {
                        log.info("--缓存删除--:key=[{}]", key);
                    }
                })
                //过期时间
                //cacheload实现类,刷新时候调用
                .expireAfterAccess(5, TimeUnit.SECONDS)
                .build((String key) -> "刷新的数据");
    }
}
