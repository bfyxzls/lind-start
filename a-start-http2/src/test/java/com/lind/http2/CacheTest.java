package com.lind.http2;

import com.github.benmanes.caffeine.cache.CacheWriter;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.RemovalCause;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Seeker1Application.class})
public class CacheTest {
    LoadingCache<String, String> cache = Caffeine.newBuilder()
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
            //过期时间,当缓存项在指定的时间段内没有被读或写就会被回收
            .expireAfterAccess(5, TimeUnit.SECONDS)
            .build((String key) -> "刷新的数据");
    ;

    @SneakyThrows
    @Test
    public void writeRead() {
        cache.put("name", "zzl");
        log.info("name:{}", cache.get("name"));
        Thread.sleep(5000);
        log.info("name:{}", cache.get("name"));

        Thread.sleep(10000);
    }
}
